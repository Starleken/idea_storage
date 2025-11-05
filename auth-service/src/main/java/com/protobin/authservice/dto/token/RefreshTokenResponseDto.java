package com.protobin.authservice.dto.token;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenResponseDto {

    @Schema(description = "Обновлённый access токен пользователя")
    private String accessToken;

    @Schema(description = "Обновлённый refresh токен пользователя")
    private String refreshToken;
}
