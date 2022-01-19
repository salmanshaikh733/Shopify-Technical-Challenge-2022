package com.example.shopify_backend_challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CsvException extends RuntimeException {

    private static final long serialVersionUID = 953303322442856647L;

    public CsvException(String message) {
        super(message);
    }
}
