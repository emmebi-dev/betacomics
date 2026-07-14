package com.betacomics.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class InsufficientStockException extends BetacomicsException {

    private static final String ERROR_CODE = "INSUFFICIENT_STOCK";

    public InsufficientStockException(String message) {
        super(message, HttpStatus.CONFLICT, ERROR_CODE);
    }

    public InsufficientStockException(String message, Throwable cause) {
        super(message, HttpStatus.CONFLICT, ERROR_CODE, cause);
    }
}
