package com.ratethis.apigateway.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException() {
        super("Error while validating data");
    }
}
