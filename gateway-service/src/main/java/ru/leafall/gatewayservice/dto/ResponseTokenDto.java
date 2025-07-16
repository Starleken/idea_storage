package ru.leafall.gatewayservice.dto;

import java.util.Set;

public class ResponseTokenDto {
    private Long id;
    private Set<Role> roles;

    public ResponseTokenDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
