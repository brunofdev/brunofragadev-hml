package com.brunofragadev.module.user.domain.repository;

import com.brunofragadev.module.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserNameOrEmail(String userName, String email);
}