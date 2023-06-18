package com.ratethis.publicservice.dto;


import com.ratethis.publicservice.dto.developerdto.DeveloperDTO;
import com.ratethis.publicservice.dto.productdto.ProductReviewDTO;
import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.Genre;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public record BookDTO(

        long id,

        String name,
        String description,

        Date releaseDate,

        String picture,

        List<Country> countries,

        List<Genre> genres,

        String type,

        int rating,

        Timestamp addDate,

        int storyMark,
        String ageRestriction,

        int artMark,

        int infoMark,

        int size,

        long estimatesQuantity,
        long reviewQuantity,

        Map<String, List<DeveloperDTO>> developers

        ) {
}
