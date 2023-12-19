package com.sukute1712.ketfilmstore.controller;

import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.payload.dto.address.AddressResponseDto;
import com.sukute1712.ketfilmstore.service.AddressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
        name = "Address",
        description = "Address controller"
)
@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
public class AddressController {

    AddressService addressService;

    @PostMapping
    public ResponseEntity<Message> addAddress(@RequestBody AddressResponseDto addressResponseDto, HttpServletRequest request) {
        return new ResponseEntity<>(addressService.addAddress(addressResponseDto, request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable UUID id,
                                                            @RequestBody AddressResponseDto addressResponseDto,
                                                            HttpServletRequest request) {
        return ResponseEntity.ok(addressService.updateAddress(id, addressResponseDto, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteAddress(@PathVariable UUID id, HttpServletRequest request) {
        return ResponseEntity.ok(addressService.deleteAddress(id, request));
    }
}
