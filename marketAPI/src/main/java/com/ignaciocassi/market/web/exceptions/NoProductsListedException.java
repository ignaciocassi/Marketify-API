package com.ignaciocassi.market.web.exceptions;

public class NoProductsListedException extends RuntimeException{
    public NoProductsListedException(String message) {
        super(message);
    }
}
