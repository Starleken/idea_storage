package ru.leafall.accountservice.dto.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenSettings {
    private SecretKey key;
    private Long expiredTime;
}
