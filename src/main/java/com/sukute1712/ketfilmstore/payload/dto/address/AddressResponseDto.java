package com.sukute1712.ketfilmstore.payload.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class AddressResponseDto {
    private UUID id;
    private String addressNumber;
    private String street;
    private String quarter;
    private String ward;
    private String district;
    private String province;
}
