package com.ratethis.productservice.dto;

import java.sql.Timestamp;

public record ProfileFilmDTO(
        long profileId,

        long productId,

        int visualMark,

        int storyMark,

        int soundMark,

        int actMark,

        int rating,

        Timestamp rateDate
) {
}
