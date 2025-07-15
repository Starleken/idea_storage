package ru.leafall.accountservice.dto.user;

import lombok.Data;

@Data
public class ChangePasswordUserDto {
    private String email;
    private String newPassword;
}
