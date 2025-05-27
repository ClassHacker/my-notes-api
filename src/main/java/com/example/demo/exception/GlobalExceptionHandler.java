package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ProductException.class)
    public ErrorResponseException handleExceptions(ProductException productException) {
        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST, productException);
        return errorResponse;
    }
}
