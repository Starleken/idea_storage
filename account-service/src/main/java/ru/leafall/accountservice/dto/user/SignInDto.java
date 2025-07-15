package ru.leafall.accountservice.dto.user;

import lombok.Data;

@Data
public class SignInDto {
    private String login;
    private String password;
}
