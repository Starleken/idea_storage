package ru.leafall.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.leafall.accountservice.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity  u where u.login = ?1")
    Optional<UserEntity> findByLogin(String login);
    @Query("SELECT u FROM UserEntity  u where u.email = ?1")
    Optional<UserEntity> findByEmail(String email);
    @Query("SELECT u FROM UserEntity  u where u.login=?1 or u.email = ?2")
    Optional<UserEntity> findByLoginOrEmail(String login, String email);
}
