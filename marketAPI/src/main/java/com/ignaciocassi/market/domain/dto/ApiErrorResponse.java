package com.ignaciocassi.market.domain.dto;

import org.springframework.http.HttpStatus;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ApiErrorResponse {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiErrorResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

}
