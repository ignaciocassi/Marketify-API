package com.ignaciocassi.marketAPI.web.exceptions;

public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException(String message) {
        super(message);
    }

}
