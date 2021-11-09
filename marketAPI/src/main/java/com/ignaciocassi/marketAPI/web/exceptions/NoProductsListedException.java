package com.ignaciocassi.marketAPI.web.exceptions;

public class NoProductsListedException extends RuntimeException{
    public NoProductsListedException(String message) {
        super(message);
    }
}
