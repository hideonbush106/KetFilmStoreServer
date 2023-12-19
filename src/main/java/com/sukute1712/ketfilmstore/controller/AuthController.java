package com.sukute1712.ketfilmstore.controller;

import com.sukute1712.ketfilmstore.jwt.JwtAuthResponse;
import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.payload.dto.auth.ForgotPasswordDto;
import com.sukute1712.ketfilmstore.payload.dto.auth.LoginDto;
import com.sukute1712.ketfilmstore.payload.dto.auth.RegisterDto;
import com.sukute1712.ketfilmstore.payload.dto.passwordresettoken.ResetNewPasswordDto;
import com.sukute1712.ketfilmstore.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(
        name = "Authentication",
        description = "Controller for authentication (login/register)"
)
public class AuthController {

    public AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) throws Exception {
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<Message> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(authService.register(registerDto), HttpStatus.CREATED);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Message> forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(authService.forgotPassword(forgotPasswordDto));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Message> resetPassword(@RequestBody ResetNewPasswordDto resetNewPasswordDto, @RequestParam(value = "token") UUID token) {
        return ResponseEntity.ok(authService.resetPassword(resetNewPasswordDto, token));
    }

}