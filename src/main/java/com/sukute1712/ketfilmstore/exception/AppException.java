package com.sukute1712.ketfilmstore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

}