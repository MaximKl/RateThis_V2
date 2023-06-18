package com.ratethis.publicservice.dto;

import com.ratethis.publicservice.dto.developerdto.DeveloperDTO;
import com.ratethis.publicservice.dto.productdto.ProductReviewDTO;
import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.GameType;
import com.ratethis.publicservice.model.Genre;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public record GameDTO(
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
        String ageRestriction,

         int visualMark,

         int storyMark,

         int gameplayMark,

         int soundMark,

         int spentTime,

         List<GameType> gameTypes,
        long estimatesQuantity,
        long reviewQuantity,
        Map<String, List<DeveloperDTO>> developers
) {

}
