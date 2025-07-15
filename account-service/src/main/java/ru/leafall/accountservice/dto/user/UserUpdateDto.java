package ru.leafall.accountservice.dto.user;

import lombok.Data;


@Data
public class UserUpdateDto {
    private Long id;
    private String name;
    private String login;
}
