package com.example.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * A custom runtime exception for handling
 * product service related errors
 */
 @Getter
@AllArgsConstructor
public class ProductServiceException extends RuntimeException {
    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;
}
