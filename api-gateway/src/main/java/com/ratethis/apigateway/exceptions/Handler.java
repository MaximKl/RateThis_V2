package com.ratethis.apigateway.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
class Handler {

    @ExceptionHandler(AlreadyExistException.class)
    ResponseEntity mailAlreadyExist(AlreadyExistException ex) {
        return new ResponseEntity(new ExceptionObject(LocalDateTime.now(),HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT.getReasonPhrase(),ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WrongEmailOrNickException.class)
    ResponseEntity wrongNickOrEmail(WrongEmailOrNickException ex) {
        return new ResponseEntity(new ExceptionObject(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    ResponseEntity validation(ValidationException ex) {
        return new ResponseEntity(new ExceptionObject(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
