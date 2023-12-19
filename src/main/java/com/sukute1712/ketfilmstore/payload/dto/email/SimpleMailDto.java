package com.sukute1712.ketfilmstore.payload.dto.email;

import lombok.Data;

@Data
public class SimpleMailDto {
    String to;
    String subject;
    String text;
}
