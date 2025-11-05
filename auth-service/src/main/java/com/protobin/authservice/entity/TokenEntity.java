package com.protobin.authservice.entity;

import com.protobin.authservice.entity.aware.CreatedAtTimestampAware;
import com.protobin.authservice.entity.listener.TimestampListener;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tokens")
@Data
@EntityListeners({TimestampListener.class})
public class TokenEntity implements CreatedAtTimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "device_info", nullable = true)
    private String deviceInfo;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "expired_at", nullable = false)
    private Long expiredAt;

    @Column(name = "revoked_at", nullable = true)
    private Long revokedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
