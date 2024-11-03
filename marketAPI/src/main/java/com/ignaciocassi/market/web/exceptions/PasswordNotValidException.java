package com.ignaciocassi.market.web.exceptions;

public class PasswordNotValidException extends RuntimeException {

    public PasswordNotValidException(String message) {
        super(message);
    }

    public PasswordNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

}
