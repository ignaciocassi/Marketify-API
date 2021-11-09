package com.ignaciocassi.marketAPI.web.exceptions;

public class NoProductsInCategoryException extends RuntimeException{
    public NoProductsInCategoryException(String message) {
        super(message);
    }
}
