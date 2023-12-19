package com.sukute1712.ketfilmstore.payload.dto.passwordresettoken;

import com.sukute1712.ketfilmstore.entity.User;

import java.time.LocalDateTime;

public class PasswordResetTokenDto {

    String token;

    LocalDateTime expireDate;

    private User user;

}
