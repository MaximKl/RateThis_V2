package com.ratethis.apigateway.exceptions;

import java.text.MessageFormat;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String item) {
        super(MessageFormat.format("{0} already exist", item));
    }
}
