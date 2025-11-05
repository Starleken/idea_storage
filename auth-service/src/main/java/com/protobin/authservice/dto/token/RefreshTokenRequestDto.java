package com.protobin.authservice.dto.token;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenRequestDto {

    @Schema(description = "refresh токен пользователя")
    private String refreshToken;
}
