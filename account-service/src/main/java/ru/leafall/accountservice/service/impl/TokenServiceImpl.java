package ru.leafall.accountservice.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import ru.leafall.accountservice.dto.token.TokenSettings;
import ru.leafall.accountservice.entity.TokenEntity;
import ru.leafall.accountservice.entity.UserEntity;
import ru.leafall.accountservice.repository.TokenRepository;
import ru.leafall.accountservice.service.TokenService;
import ru.leafall.accountservice.utils.TimeUtils;
import ru.leafall.mainstarter.utils.ThrowableUtils;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    public static final String ROLES_CLAIMS = "rol";
    public static final String USERID_CLAIMS = "uid";

    private final TokenSettings accessTokenSettings;
    private final TokenSettings refreshTokenSettings;
    private final TokenRepository tokenRepository;

    public TokenServiceImpl(@Value("${jwt.tokens.access.key}") String accessKey,
                            @Value("${jwt.tokens.refresh.key}") String refreshKey,
                            @Value("${jwt.tokens.refresh.expiredTime}")Long refreshMinutesExpired,
                            @Value("${jwt.tokens.access.expiredTime}") Long accessMinutesExpired,
                            @Autowired TokenRepository tokenRepository) {
        var accessBytes = Base64Coder.decode(accessKey);
        var accessSecretKey = Keys.hmacShaKeyFor(accessBytes);
        var refreshBytes = Base64Coder.decode(refreshKey);
        var refreshSecretKey = Keys.hmacShaKeyFor(refreshBytes);

        this.accessTokenSettings = new TokenSettings(accessSecretKey, accessMinutesExpired);
        this.refreshTokenSettings = new TokenSettings(refreshSecretKey, refreshMinutesExpired);
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String generateAccessToken(String refreshToken) {
        var claims = getRefreshClaims(refreshToken);
        return Jwts.builder()
                .signWith(accessTokenSettings.getKey())
                .subject(claims.getSubject())
                .expiration(TimeUtils.getDateFromUtcExpired(accessTokenSettings.getExpiredTime()))
                .claim(ROLES_CLAIMS, claims.get(ROLES_CLAIMS))
                .claim(USERID_CLAIMS, claims.get(USERID_CLAIMS))
                .compact();
    }

    @Override
    public String generateRefreshToken(UserEntity user) {
        var generatedToken = generateToken(user, refreshTokenSettings);
        return generatedToken;
    }

    @Override
    @Transactional
    public TokenEntity saveRefreshToken(UserEntity user, String generatedToken) {
        var token = new TokenEntity();
        token.setUser(user);
        token.setToken(generatedToken);
        token.setExpiredAt(TimeUtils.getTimeFromUtcExpired(refreshTokenSettings.getExpiredTime()));
        return tokenRepository.save(token);
    }

    @Override
    public TokenEntity deleteRefreshToken(String token) {
        var entity = tokenRepository.findByToken(token).orElseThrow(ThrowableUtils::getNotFoundException);
        tokenRepository.delete(entity);
        return entity;
    }

    @Override
    public void validateAccessToken(String accessToken) {
        validateToken(accessToken, accessTokenSettings.getKey());
    }

    @Override
    public void validateRefreshToken(String refreshToken) {
        validateToken(refreshToken, refreshTokenSettings.getKey());
    }

    private String generateToken(UserEntity user, TokenSettings settings) {
        return Jwts.builder()
                .subject(user.getLogin())
                .expiration(TimeUtils.getDateFromUtcExpired(settings.getExpiredTime()))
                .claims(generateClaims(user))
                .signWith(settings.getKey())
                .compact();
    }

    private void validateToken(String token, SecretKey key) {
        Jwts.parser().setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    private Map<String, Object> generateClaims(UserEntity user) {
        Map<String, Object> map = new HashMap<>();
        map.put(ROLES_CLAIMS, user.getRoles());
        map.put(USERID_CLAIMS, user.getId());
        return map;
    }
    @Override
    public Claims getAccessClaims(String token) {
        return Jwts.parser().setSigningKey(accessTokenSettings.getKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }
    private Claims getRefreshClaims(String token) {
        return Jwts.parser().setSigningKey(refreshTokenSettings.getKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }
}
