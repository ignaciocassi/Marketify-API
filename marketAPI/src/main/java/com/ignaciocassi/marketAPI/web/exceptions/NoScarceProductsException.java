package com.ignaciocassi.marketAPI.web.exceptions;

public class NoScarceProductsException extends RuntimeException {
    public NoScarceProductsException(String message) {
        super(message);
    }
}
