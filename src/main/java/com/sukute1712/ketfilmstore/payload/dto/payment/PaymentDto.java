package com.sukute1712.ketfilmstore.payload.dto.payment;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentDto {

    private UUID id;

    private String method;
}
