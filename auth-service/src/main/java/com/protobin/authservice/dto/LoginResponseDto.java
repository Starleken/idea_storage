package com.protobin.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoginResponseDto {

    @Schema(description = "UUID пользователя, прошедшего авторизацию")
    private UUID userId;

    @Schema(description = "Выданный access токен")
    private String accessToken;
}
