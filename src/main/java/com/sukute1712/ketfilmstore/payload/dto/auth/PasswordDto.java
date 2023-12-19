package com.sukute1712.ketfilmstore.payload.dto.auth;

import lombok.Data;

@Data
public class PasswordDto {
    private String oldPassword;
    private String newPassword;
}
