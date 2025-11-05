package com.protobin.authservice.entity;

import com.protobin.authservice.entity.aware.CreatedAtTimestampAware;
import com.protobin.authservice.entity.listener.TimestampListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@EntityListeners({TimestampListener.class})
public class UserEntity implements CreatedAtTimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false, unique = true)
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
