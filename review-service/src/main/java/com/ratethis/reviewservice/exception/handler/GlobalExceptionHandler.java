package com.ratethis.reviewservice.exception.handler;

import com.ratethis.reviewservice.exception.ExceptionObject;
import com.ratethis.reviewservice.exception.IdentificationException;
import com.ratethis.reviewservice.exception.ReviewNotFoundException;
import com.ratethis.reviewservice.exception.TypeErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = TypeErrorException.class)
    protected ResponseEntity<Object> handleTypeError(TypeErrorException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = ReviewNotFoundException.class)
    protected ResponseEntity<Object> handleProfileOrProductNotFound(ReviewNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.append(e.getDefaultMessage()+"; "));
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errors.toString()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = IdentificationException.class)
    protected ResponseEntity<Object> handleIdentificationException(IdentificationException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), ex.getMessage()), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

}