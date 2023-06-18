package com.ratethis.productservice.exception;

import java.text.MessageFormat;

public class ProductOrUserNotFoundException extends RuntimeException{
    public ProductOrUserNotFoundException(String productId, String userId) {
        super(MessageFormat.format("Product with ID {0} or User with ID {1} not found",userId, productId));
    }
}
