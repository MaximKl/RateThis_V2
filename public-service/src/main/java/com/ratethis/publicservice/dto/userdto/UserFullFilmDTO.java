package com.ratethis.publicservice.dto.userdto;

import com.ratethis.publicservice.dto.developerdto.DeveloperDTO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public record UserFullFilmDTO(
        long id,
        String name,

        String description,

        Date releaseDate,

        String picture,

        List<String> countries,

        List<String> genres,

        Timestamp addDate,

        String type,

        String ageRestriction,
        int commonRating,
        int commonVisualMark,

        int commonStoryMark,

        int commonSoundMark,
        int commonActMark,
        int rating,
        int filmSize,

        int visualMark,

        int storyMark,

        int soundMark,
        int actMark,
        long estimatesQuantity,
        long reviewQuantity,

        long reviewId,
        String reviewBody,

        int reviewLikes,
        int reviewDislikes,
        Timestamp reviewTime,
        Timestamp reviewEditTime,
        boolean reviewIsEdit,

        Timestamp rateDate,

        Map<String, List<DeveloperDTO>> developers) {
}
