package com.ratethis.publicservice.exception.handler;

import com.ratethis.publicservice.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ProductNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = ProfileNotFoundException.class)
    protected ResponseEntity<Object> handleProfileNotFound(ProfileNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage()),  new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = ProductAndProfileException.class)
    protected ResponseEntity<Object> handleProfileAndProductNotFound(ProductAndProfileException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage()),  new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = TypeErrorException.class)
    protected ResponseEntity<Object> handleProfileAndProductNotFound(TypeErrorException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()),  new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}