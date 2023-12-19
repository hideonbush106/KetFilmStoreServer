package com.sukute1712.ketfilmstore.service;

import com.sukute1712.ketfilmstore.entity.PasswordResetToken;
import com.sukute1712.ketfilmstore.entity.User;
import com.sukute1712.ketfilmstore.exception.AppException;
import com.sukute1712.ketfilmstore.exception.ResourceNotFoundException;
import com.sukute1712.ketfilmstore.jwt.JwtTokenProvider;
import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.payload.dto.auth.ForgotPasswordDto;
import com.sukute1712.ketfilmstore.payload.dto.auth.LoginDto;
import com.sukute1712.ketfilmstore.payload.dto.auth.RegisterDto;
import com.sukute1712.ketfilmstore.payload.dto.passwordresettoken.ResetNewPasswordDto;
import com.sukute1712.ketfilmstore.repository.PasswordResetTokenRepository;
import com.sukute1712.ketfilmstore.repository.UserRepository;
import com.sukute1712.ketfilmstore.utils.Role;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private ModelMapper modelMapper;
    private EmailService emailService;
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public String login(LoginDto loginDto) throws Exception {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    public Message register(RegisterDto registerDto) {

        // Add check for username exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Username is already exists");
        }

        // Add check for email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Email is already exists");
        }

        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = Role.valueOf(Role.ROLE_CUSTOMER.name());
        roles.add(userRole);
        user.setRole(roles);

        userRepository.save(user);

        return new Message("User registered successfully");
    }

    public Message forgotPassword(ForgotPasswordDto forgotPasswordDto) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmail(forgotPasswordDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User"));

        UUID token = UUID.randomUUID();

        PasswordResetToken passwordResetToken = new PasswordResetToken(token, LocalDateTime.now().plusMinutes(30), user);
        user.setPasswordResetToken(passwordResetToken);
        userRepository.save(user);

        Context context = new Context();
        context.setVariable("token", token.toString());

        emailService.sendMessage(forgotPasswordDto.getEmail(),
                "Reset password",
                user.getPasswordResetToken().getToken().toString(),
                context,
                "reset-password"
        );
        return new Message("Verification email has been sent");
    }

    public Message resetPassword(ResetNewPasswordDto resetNewPasswordDto, UUID token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token"));
        if (LocalDateTime.now().isAfter(passwordResetToken.getExpireDate())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Token expired");
        } else {
            User user = userRepository.findByPasswordResetToken(passwordResetToken)
                    .orElseThrow(() -> new ResourceNotFoundException("User"));
            user.setPassword(passwordEncoder.encode(resetNewPasswordDto.getPassword()));
            userRepository.save(user);
            passwordResetTokenRepository.deleteByToken(token);
            return new Message("Password reset successfully");
        }

    }

}
