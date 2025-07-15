package ru.leafall.accountservice.dto.token;

import lombok.Data;

@Data
public class TokenRefreshDto {
    private String refreshToken;
}
