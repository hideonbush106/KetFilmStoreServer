package com.sukute1712.ketfilmstore.controller;

import com.sukute1712.ketfilmstore.entity.User;
import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.payload.dto.auth.PasswordDto;
import com.sukute1712.ketfilmstore.payload.dto.user.UserListDto;
import com.sukute1712.ketfilmstore.payload.dto.user.UserProfileRequestDto;
import com.sukute1712.ketfilmstore.payload.dto.user.UserProfileResponseDto;
import com.sukute1712.ketfilmstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
        name = "User",
        description = "User controller"
)
public class UserController {

    public UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponseDto> getOwnUser(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getOwnUser(request));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Get all user list",
            description = "Admin only"
    )
    public ResponseEntity<List<UserListDto>> getAll() {
        List<UserListDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Message> changePassword(@RequestBody PasswordDto password, HttpServletRequest request) {
        return ResponseEntity.ok(userService.changePassword(password, request));
    }
    
    @PutMapping("/update-profile")
    public ResponseEntity<UserProfileResponseDto> update(@RequestBody UserProfileRequestDto userProfileRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok(userService.update(userProfileRequestDto, request));
    }

}
