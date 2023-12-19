package com.sukute1712.ketfilmstore.repository;

import com.sukute1712.ketfilmstore.entity.PasswordResetToken;
import com.sukute1712.ketfilmstore.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {

    Optional<PasswordResetToken> findByToken(UUID token);

    void deleteByUser(User user);

    @Transactional
    @Modifying
    @Query("DELETE PasswordResetToken p WHERE p.token = :token")
    void deleteByToken(UUID token);
}
