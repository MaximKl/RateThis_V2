package com.ratethis.profileservice.exception;


public class ValidationException extends RuntimeException{
    public ValidationException() {
        super("valid");
    }
}
