package com.ratethis.productservice.exception;

public class IdentificationException extends RuntimeException{
    public IdentificationException() {
        super("Identification error");
    }
}
