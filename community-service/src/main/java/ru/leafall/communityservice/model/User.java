package ru.leafall.communityservice.model;

import lombok.Data;

import java.util.Set;

@Data
public class User {
    private Long id;

    private String name;
    private String email;

    private Long createdAt;

    private Set<Role> roles;
}
