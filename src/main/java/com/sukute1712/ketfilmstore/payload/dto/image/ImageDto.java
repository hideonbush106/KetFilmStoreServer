package com.sukute1712.ketfilmstore.payload.dto.image;

import lombok.Data;

import java.util.UUID;

@Data
public class ImageDto {

    private UUID id;

    private String source;

    private String publicId;

}
