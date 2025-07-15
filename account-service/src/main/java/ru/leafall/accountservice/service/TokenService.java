package ru.leafall.accountservice.service;

import io.jsonwebtoken.Claims;
import ru.leafall.accountservice.entity.TokenEntity;
import ru.leafall.accountservice.entity.UserEntity;

public interface TokenService {

    String generateAccessToken(String refreshToken);
    String generateRefreshToken(UserEntity user);
    TokenEntity saveRefreshToken(UserEntity user, String generatedToken);
    TokenEntity deleteRefreshToken(String token);

    void validateAccessToken(String accessToken);
    void validateRefreshToken(String refreshToken);
    Claims getAccessClaims(String token);

}
