package com.ratethis.publicservice.dto.userdto;

import com.ratethis.publicservice.dto.developerdto.DeveloperDTO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public record UserGameDTO (
        long id,
        String name,

        Date releaseDate,

        String picture,

        List<String> countries,

        List<String> genres,

        String type,

        String ageRestriction,

        int commonRating,
        int rating,

        int visualMark,

        int storyMark,

        int gameplayMark,
        int soundMark,
        int spentTime,

        Timestamp rateDate

){
}
