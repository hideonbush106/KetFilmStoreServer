package com.sukute1712.ketfilmstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "password_reset_token")
public class PasswordResetToken extends AbstractPersistable<UUID> {

    private UUID token;

    private LocalDateTime expireDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
