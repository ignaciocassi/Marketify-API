package com.ignaciocassi.marketAPI.web.exceptions;

public class NoCategoriesListedException extends RuntimeException{
    public NoCategoriesListedException(String message) {
        super(message);
    }
}
