package com.protobin.authservice.service;

import com.protobin.authservice.dto.token.RefreshTokenResponseDto;
import com.protobin.authservice.entity.RequestInfo;
import com.protobin.authservice.entity.TokenHolder;
import io.jsonwebtoken.Claims;

import java.util.UUID;

public interface TokenService {

    String generateAccessToken(UUID userId);
    String generateRefreshToken(UUID userId, RequestInfo requestInfo);
    void validateAccessToken(String token);
    void validateRefreshToken(String token);
    TokenHolder refresh(String token, RequestInfo requestInfo);
    Claims getAccessClaims(String token);
    void deleteByToken(String refreshToken);
}
