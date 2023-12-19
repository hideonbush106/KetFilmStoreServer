package com.sukute1712.ketfilmstore.payload.dto.transaction;

import com.sukute1712.ketfilmstore.entity.Payment;
import com.sukute1712.ketfilmstore.payload.dto.address.AddressResponseDto;
import com.sukute1712.ketfilmstore.payload.dto.order.OrderDto;
import com.sukute1712.ketfilmstore.payload.dto.payment.PaymentDto;
import com.sukute1712.ketfilmstore.utils.OrderStatus;
import lombok.Data;

import java.util.UUID;
@Data
public class TransactionDto {

    private UUID id;

    private OrderDto order;

    private OrderStatus status;

    private PaymentDto payment;

    private AddressResponseDto address;

}
