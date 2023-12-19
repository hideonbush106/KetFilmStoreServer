package com.sukute1712.ketfilmstore;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KetFilmStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(KetFilmStoreApplication.class, args);
    }

}
