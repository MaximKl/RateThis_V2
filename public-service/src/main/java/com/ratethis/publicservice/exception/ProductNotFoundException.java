package com.ratethis.publicservice.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String productType, String id) {
        super(productType+" with id "+id+" not found");
    }
}
