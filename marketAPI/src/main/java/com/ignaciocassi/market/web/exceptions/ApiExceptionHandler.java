package com.ignaciocassi.market.web.exceptions;

import com.ignaciocassi.market.domain.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final String zoneId = "America/Argentina/Buenos_Aires";
    
    @ExceptionHandler(value = {PasswordNotValidException.class})
    public ResponseEntity<Object> handleApiRequestException(PasswordNotValidException e) {
        HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notAcceptable
        );
        return new ResponseEntity<>(apiErrorResponse, notAcceptable);
    }

    @ExceptionHandler(value = {UsernameNotValidException.class})
    public ResponseEntity<Object> handleApiRequestException(UsernameNotValidException e) {
        HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notAcceptable
        );
        return new ResponseEntity<>(apiErrorResponse, notAcceptable);
    }

    @ExceptionHandler(value = {UsernameExistsException.class})
    public ResponseEntity<Object> handleApiRequestException(UsernameExistsException e) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                conflict
        );
        return new ResponseEntity<>(apiErrorResponse, conflict);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> handleApiRequestException(BadCredentialsException e) {
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                unauthorized
        );
        return new ResponseEntity<>(apiErrorResponse, unauthorized);
    }

    @ExceptionHandler(value = {NoProductsListedException.class})
    public ResponseEntity<Object> handleApiRequestException(NoProductsListedException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notFound
        );
        return new ResponseEntity<>(apiErrorResponse, notFound);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(ProductNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notFound
        );
        return new ResponseEntity<>(apiErrorResponse, notFound);
    }

    @ExceptionHandler(value = {NoProductsInCategoryException.class})
    public ResponseEntity<Object> handleApiRequestException(NoProductsInCategoryException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notFound
        );
        return new ResponseEntity<>(apiErrorResponse, notFound);
    }

    @ExceptionHandler(value = {NoScarceProductsException.class})
    public ResponseEntity<Object> handleApiRequestException(NoScarceProductsException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notFound
        );
        return new ResponseEntity<>(apiErrorResponse, notFound);
    }

    @ExceptionHandler(value = {CategoryNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(CategoryNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notFound
        );
        return new ResponseEntity<>(apiErrorResponse, notFound);
    }

    @ExceptionHandler(value = {NoCategoriesListedException.class})
    public ResponseEntity<Object> handleApiRequestException(NoCategoriesListedException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notFound
        );
        return new ResponseEntity<>(apiErrorResponse, notFound);
    }

    @ExceptionHandler(value = {PurchaseNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(PurchaseNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notFound
        );
        return new ResponseEntity<>(apiErrorResponse, notFound);
    }

    @ExceptionHandler(value = {ClientNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(ClientNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                notFound
        );
        return new ResponseEntity<>(apiErrorResponse, notFound);
    }

    @ExceptionHandler(value = {ProductDeleteException.class})
    public ResponseEntity<Object> handleApiRequestException(ProductDeleteException e) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                conflict
        );
        return new ResponseEntity<>(apiErrorResponse, conflict);
    }

    @ExceptionHandler(value = {ProductAlreadyExistsException.class})
    public ResponseEntity<Object> handleApiRequestException(ProductAlreadyExistsException e) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                e.getMessage(),
                conflict
        );
        return new ResponseEntity<>(apiErrorResponse, conflict);
    }

}
