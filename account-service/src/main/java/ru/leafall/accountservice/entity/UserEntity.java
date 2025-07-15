package ru.leafall.accountservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.leafall.accountservice.entity.aware.TimestampAware;
import ru.leafall.accountservice.entity.converter.RoleSetConverter;
import ru.leafall.accountservice.entity.listener.TimestampListener;

import java.util.Set;

@Entity
@Data
@Table(name = "users")
@EntityListeners({TimestampListener.class})
public class UserEntity implements TimestampAware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "roles", nullable = false)
    @Convert(converter = RoleSetConverter.class)
    private Set<Role> roles;
}
