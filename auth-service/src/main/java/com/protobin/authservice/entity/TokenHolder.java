package com.protobin.authservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class TokenHolder {

    private final String accessToken;
    private final String refreshToken;
}
