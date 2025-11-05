package com.protobin.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogoutRequestDto {

    @Schema(description = "Refresh токен для удаления")
    private String refreshToken;
}
