package com.sukute1712.ketfilmstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@NoArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
        name = "Root",
        description = "Demo controller"
)
public class RootController {

    @Value("${server.port}")
    private String port;

    @GetMapping
    public String root() {
        return "<code>[⚡] Server is running on port " + port + "</code>";
    }
    @Operation(
            summary = "Demo API for admin",
            description = "Copy JWT token (login with admin account) and put to auth field fong:fong"
    )
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(
            responseCode = "200",
            description = "Login success for admin"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token expired or incorrect"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Unauthorized - incorrect role"
    )
    public String helloAdmin() {
        return "Hello admin";
    }

    @Operation(
            summary = "Demo API for customer",
            description = "Copy JWT token (login with admin account) and put to auth field su:su"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Login success for customer"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token expired or incorrect"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Unauthorized - incorrect role"
    )
    @GetMapping("/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String helloCustomer() {
        return "Hello customer";
    }

}
