package com.ratethis.publicservice.dto.productdto;

import java.sql.Timestamp;

public record ProductReviewDTO(

        long id,

        String body,

        int like,

        int dislike,

        Timestamp time,

        String name,
        String photo,
        String color,
        String role,

        boolean isEdit,
        Timestamp editTime
) {
}
