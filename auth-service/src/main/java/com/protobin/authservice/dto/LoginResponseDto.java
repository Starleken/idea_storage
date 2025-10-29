package com.protobin.authservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoginResponseDto {

    private UUID id;
    private String refreshToken;
    private String accessToken;
}
