package com.sukute1712.ketfilmstore.payload.dto.orderproduct;

import com.sukute1712.ketfilmstore.entity.Product;
import com.sukute1712.ketfilmstore.payload.dto.product.ProductDto;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderProductDto {

    private UUID id;

    private ProductDto product;

    private long quantity;

    private long totalPricePerProducts;
}
