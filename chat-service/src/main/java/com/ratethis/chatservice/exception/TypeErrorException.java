package com.ratethis.chatservice.exception;

public class TypeErrorException extends RuntimeException{
    public TypeErrorException() {
        super("wrong type of path variables");
    }
}
