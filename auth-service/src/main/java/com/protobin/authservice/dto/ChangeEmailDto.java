package com.protobin.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ChangeEmailDto {

    @Schema(description = "UUID пользователя")
    private UUID id;

    @Schema(description = "Новая почта пользователя", example = "john.doe@example.com")
    private String newEmail;
}
