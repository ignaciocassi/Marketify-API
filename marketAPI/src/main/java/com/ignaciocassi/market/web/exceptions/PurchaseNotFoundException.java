package com.ignaciocassi.market.web.exceptions;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException(String message) {
        super(message);
    }
}
