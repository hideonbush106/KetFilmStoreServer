package com.sukute1712.ketfilmstore.payload.dto.product;

import com.sukute1712.ketfilmstore.entity.Image;
import com.sukute1712.ketfilmstore.payload.dto.image.ImageDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String name;

    private long price;

    private long quantity;

    private long originalISO;

    private String size;

    private String category;

    private List<String> status;

    private List<String> date;

    private List<String> modelNo;

    private List<ImageDto> images;
}
