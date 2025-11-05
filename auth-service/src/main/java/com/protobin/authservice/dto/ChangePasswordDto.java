package com.protobin.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ChangePasswordDto {

    @Schema(description = "UUID пользователя")
    private UUID id;

    @Schema(description = "Текущий пароль пользователя", example = "Currentpassword154")
    private String oldPassword;

    @Schema(description = "Новый пароль пользователя", example = "Newpassword152")
    private String newPassword;
}
