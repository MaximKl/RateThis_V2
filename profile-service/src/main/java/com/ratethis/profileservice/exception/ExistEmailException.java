package com.ratethis.profileservice.exception;

public class ExistEmailException extends RuntimeException {
    public ExistEmailException() {
        super("This email already registered");
    }
}
