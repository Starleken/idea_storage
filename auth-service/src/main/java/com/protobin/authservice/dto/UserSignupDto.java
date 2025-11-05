package com.protobin.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignupDto {

    @Schema(description = "Почта пользователя для регистрации", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Логин пользователя для регистрации", example = "starleken")
    private String username;

    @Schema(description = "Пароль пользователя для регистрации", example = "Currentpassword154")
    private String password;
}
