package ru.leafall.accountservice.dto.user;

import lombok.Data;
import ru.leafall.accountservice.entity.Role;

import java.util.Set;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Long createdAt;
    private Set<Role> roles;
}
