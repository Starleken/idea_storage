package ru.leafall.accountservice.dto.user;

import lombok.Data;

@Data
public class ChangeEmailUserDto {
    private String login;
    private String newEmail;
}
