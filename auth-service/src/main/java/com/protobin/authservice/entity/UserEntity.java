package com.protobin.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "banned_at", nullable = true)
    private Long bannedAt;

    @Column(name = "activated_at", nullable = true)
    private Long activatedAt;

    @Column(name = "deleted_at", nullable = true)
    private Long deletedAt;
}
