package com.example.shopify_backend_challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException{

    private static final long serialVersionUID = 7804120370687530002L;

    public InvalidInputException(String message) {
        super(message);
    }}
