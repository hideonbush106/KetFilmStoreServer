package com.sukute1712.ketfilmstore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName;

    public ResourceNotFoundException(String resourceName) {
        super(String.format("%s not found", resourceName)); // Post not found with id : 1
        this.resourceName = resourceName;
    }
}
