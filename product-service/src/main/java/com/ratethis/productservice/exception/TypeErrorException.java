package com.ratethis.productservice.exception;

public class TypeErrorException extends RuntimeException{
    public TypeErrorException() {
        super("wrong type of path variables");
    }
}
