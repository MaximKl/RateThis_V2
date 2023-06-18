package com.ratethis.profileservice.exception;

public class ExistNickException extends RuntimeException{
    public ExistNickException() {
        super("This nick already registered");
    }
}
