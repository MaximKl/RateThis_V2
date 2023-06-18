package com.ratethis.publicservice.dto.userdto;

import com.ratethis.publicservice.dto.developerdto.DeveloperDTO;
import com.ratethis.publicservice.dto.productdto.ProductReviewDTO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public record UserFullGameDTO(
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

        List<String> gameTypes,

        int commonRating,
        int commonVisualMark,

        int commonStoryMark,

        int commonGameplayMark,
        int commonSoundMark,
        int commonSpentTime,
        int rating,

        int visualMark,

        int storyMark,

        int gameplayMark,
        int soundMark,
        int spentTime,
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
