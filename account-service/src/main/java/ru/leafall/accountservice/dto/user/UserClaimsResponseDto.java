package ru.leafall.accountservice.dto.user;

import lombok.Builder;
import lombok.Data;
import ru.leafall.accountservice.entity.Role;

import java.util.Set;

@Data
@Builder
public class UserClaimsResponseDto {
    private Long id;
    private Set<Role> roles;
}
