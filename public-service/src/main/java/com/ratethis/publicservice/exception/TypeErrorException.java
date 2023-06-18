package com.ratethis.publicservice.exception;

public class TypeErrorException extends RuntimeException{
    public TypeErrorException() {
        super("wrong type of path variables");
    }
}
