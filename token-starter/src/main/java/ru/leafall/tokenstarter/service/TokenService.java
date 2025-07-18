package ru.leafall.tokenstarter.service;

import io.jsonwebtoken.Claims;

public interface TokenService {
    Claims getClaims(String token);
    void validateToken(String token);
}
