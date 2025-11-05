package com.protobin.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {

    @Schema(description = "Почта пользователя для авторизации", example = "john.doe@example.co")
    private String email;

    @Schema(description = "Текущий пароль пользователя", example = "Currentpassword154")
    private String password;
}
