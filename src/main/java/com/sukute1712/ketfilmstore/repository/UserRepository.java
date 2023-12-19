package com.sukute1712.ketfilmstore.repository;

import com.sukute1712.ketfilmstore.entity.PasswordResetToken;
import com.sukute1712.ketfilmstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsernameOrEmail(String email, String username);

    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByPasswordResetToken(PasswordResetToken passwordResetToken);
}
