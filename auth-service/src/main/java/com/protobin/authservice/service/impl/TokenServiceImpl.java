package com.protobin.authservice.service.impl;

import com.protobin.authservice.dto.token.RefreshTokenResponseDto;
import com.protobin.authservice.entity.RequestInfo;
import com.protobin.authservice.entity.TokenEntity;
import com.protobin.authservice.entity.UserEntity;
import com.protobin.authservice.exception.TokenIsUsedException;
import com.protobin.authservice.repository.TokenRepository;
import com.protobin.authservice.repository.UserRepository;
import com.protobin.authservice.service.TokenService;
import com.protobin.authservice.utils.ExceptionUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

import static com.protobin.authservice.utils.ExceptionUtils.*;
import static com.protobin.authservice.utils.ExceptionUtils.getEntityNotFoundException;
import static com.protobin.authservice.utils.TimeUtils.getCurrentTimeFromUTC;
import static com.protobin.authservice.utils.TimeUtils.getExpiredDateFromUTC;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    private final SecretKey secretAccessKey;
    private final SecretKey secretRefreshKey;

    private final long accessTime;
    private final long refreshTime;

    public TokenServiceImpl(@Autowired TokenRepository tokenRepository,
                            @Autowired UserRepository userRepository,
                            @Value("${jwt.access.secretKey}") String secretAccessKey,
                            @Value("${jwt.refresh.secretKey}") String secretRefreshKey,
                            @Value("${jwt.access.ttlMinutes}") long accessTime,
                            @Value("${jwt.refresh.ttlMinutes}") long refreshTime) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.secretAccessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretAccessKey));
        this.secretRefreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretRefreshKey));
        this.accessTime = accessTime;
        this.refreshTime = refreshTime;
    }

    @Override
    public String generateAccessToken(UUID userId) {
        Date expirationTime = getExpiredDateFromUTC(accessTime);

        var foundUser = userRepository.findById(userId)
                .orElseThrow(() -> getEntityNotFoundException(UserEntity.class));

        return generateToken(foundUser.getId(), secretAccessKey, expirationTime);
    }

    @Override
    public String generateRefreshToken(UUID userId, RequestInfo requestInfo) {
        Date expirationTime = getExpiredDateFromUTC(refreshTime);

        var foundUser = userRepository.findById(userId).orElseThrow(
                () -> getEntityNotFoundException(UserEntity.class));

        var refresh = generateToken(foundUser.getId(), secretRefreshKey, expirationTime);

        var tokenEntity = new TokenEntity();
        tokenEntity.setToken(refresh);
        tokenEntity.setUser(foundUser);
        tokenEntity.setCreatedAt(getCurrentTimeFromUTC());
        tokenEntity.setExpiredAt(expirationTime.getTime());
        tokenEntity.setIpAddress(requestInfo.getIpAddress());
        tokenEntity.setDeviceInfo(requestInfo.getDeviceInfo());
        tokenRepository.save(tokenEntity);

        return refresh;
    }

    private String generateToken(UUID userId, SecretKey key, Date expirationTime) {
        var foundUser = userRepository.findById(userId)
                .orElseThrow(() -> getEntityNotFoundException(UserEntity.class));

        return Jwts.builder()
                .setSubject(foundUser.getEmail())
                .setExpiration(expirationTime)
                .signWith(key)
                .compact();
    }

    @Override
    public void validateAccessToken(String token) {
        validateToken(secretAccessKey, token);
    }

    @Override
    @Transactional(noRollbackFor = TokenIsUsedException.class)
    public void validateRefreshToken(String token) {
        validateToken(secretRefreshKey, token);

        var foundToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> getEntityNotFoundException(TokenEntity.class));

        if (foundToken.getRevokedAt() != null) {
            throwTokenIsUsedException("Token is revoked");
        }
    }

    @Override
    @Transactional(noRollbackFor = TokenIsUsedException.class)
    public RefreshTokenResponseDto refresh(String token, RequestInfo request) {
        var entity = tokenRepository.findByToken(token)
                .orElseThrow(() -> getEntityNotFoundException(TokenEntity.class));

        validateRefreshToken(entity.getToken());
        revokeToken(entity.getId());

        return RefreshTokenResponseDto.builder()
                .accessToken(generateAccessToken(entity.getUser().getId()))
                .refreshToken(generateRefreshToken(entity.getUser().getId(), request))
                .build();
    }

    @Override
    public Claims getAccessClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretAccessKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public void deleteByToken(String refreshToken) {
        var found = tokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> getEntityNotFoundException(TokenEntity.class));

        tokenRepository.deleteById(found.getId());
    }

    private void validateToken(SecretKey key, String token) {
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    private void revokeToken(UUID tokenId) {
        var entity = tokenRepository.findById(tokenId)
                .orElseThrow(() -> getEntityNotFoundException(TokenEntity.class));

        entity.setRevokedAt(getCurrentTimeFromUTC());
        tokenRepository.save(entity);
    }
}
