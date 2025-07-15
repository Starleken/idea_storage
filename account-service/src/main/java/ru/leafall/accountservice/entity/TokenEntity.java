package ru.leafall.accountservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.leafall.accountservice.entity.aware.TimestampAware;
import ru.leafall.accountservice.entity.listener.TimestampListener;

@Entity
@Data
@Table(name = "tokens")
@EntityListeners({TimestampListener.class})
public class TokenEntity implements TimestampAware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "expired_at", nullable = false)
    private Long expiredAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
