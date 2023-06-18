package com.ratethis.apigateway.exceptions;

import java.time.LocalDateTime;

public record ExceptionObject(
        LocalDateTime timestamp,
        int code,
        String error,
        String detail
) {
}
