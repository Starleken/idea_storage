package ru.leafall.accountservice.dto.user;

import lombok.Data;

@Data
public class UserCreateDto {
    private String name;
    private String password;
    private String email;
    private String login;
}
