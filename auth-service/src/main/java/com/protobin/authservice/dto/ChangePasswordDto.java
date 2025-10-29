package com.protobin.authservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ChangePasswordDto {

    private UUID id;
    private String oldPassword;
    private String newPassword;
}
