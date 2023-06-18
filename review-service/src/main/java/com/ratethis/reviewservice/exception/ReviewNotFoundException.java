package com.ratethis.reviewservice.exception;

import java.text.MessageFormat;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(String productId) {
        super(MessageFormat.format("Review for product {0} not found", productId));
    }
}
