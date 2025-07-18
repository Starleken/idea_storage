package ru.leafall.tokenstarter.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import ru.leafall.tokenstarter.service.TokenService;

import javax.crypto.SecretKey;

@Service
public class TokenServiceImpl implements TokenService {
    private final SecretKey key;

    public TokenServiceImpl(String accessKey) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessKey));
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(key)
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    @Override
    public void validateToken(String token) {
        Jwts.parser().verifyWith(key)
                .build()
                .parseClaimsJws(token);
    }
}
