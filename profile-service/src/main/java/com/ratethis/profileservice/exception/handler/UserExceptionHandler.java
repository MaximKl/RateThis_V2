package com.ratethis.profileservice.exception.handler;

import com.ratethis.profileservice.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ExistNickException.class)
    protected ResponseEntity<Object> handleNick(ExistNickException ex, WebRequest request) {
        String bodyOfResponse = "nick_exist";
        return handleExceptionInternal(ex, bodyOfResponse,  new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = ExistEmailException.class)
    protected ResponseEntity<Object> handleMail(ExistEmailException ex, WebRequest request) {
        String bodyOfResponse = "mail_exist";
        return handleExceptionInternal(ex, bodyOfResponse,  new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = WrongEmailOrNickException.class)
    protected ResponseEntity<Object> handleMail(WrongEmailOrNickException ex, WebRequest request) {
        String bodyOfResponse = "wrong_nick_or_mail";
        return handleExceptionInternal(ex, bodyOfResponse,  new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<Object> handleValidation(ValidationException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = TypeErrorException.class)
    protected ResponseEntity<Object> handleTypeError(TypeErrorException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = IdentificationException.class)
    protected ResponseEntity<Object> handleIdentificationException(IdentificationException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionObject(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), ex.getMessage()), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

}