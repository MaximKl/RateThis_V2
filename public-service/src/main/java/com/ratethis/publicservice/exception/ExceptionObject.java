package com.ratethis.publicservice.exception;

import java.time.LocalDateTime;

public record ExceptionObject(
        LocalDateTime timestamp,
        int code,
        String error,
        String detail
) {
}
