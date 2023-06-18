package com.ratethis.publicservice.exception;

public class ProductAndProfileException extends RuntimeException{
    public ProductAndProfileException(String productType, String user, String product) {
        super("Profile with nickname "+user+" or "+productType+" with id "+product+" not found");
    }
}
