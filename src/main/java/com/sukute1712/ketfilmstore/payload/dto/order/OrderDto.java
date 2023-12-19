package com.sukute1712.ketfilmstore.payload.dto.order;

import com.sukute1712.ketfilmstore.entity.OrderProduct;
import com.sukute1712.ketfilmstore.payload.dto.orderproduct.OrderProductDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {

    private UUID id;

    private List<OrderProductDto> orderProducts;

    private long totalPrice;

}
