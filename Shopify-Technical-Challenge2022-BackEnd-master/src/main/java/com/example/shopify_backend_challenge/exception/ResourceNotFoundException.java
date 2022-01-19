package com.example.shopify_backend_challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3688593408959561304L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
