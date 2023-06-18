package com.ratethis.productservice.dto;

import java.sql.Timestamp;

public record ProfileGameDTO (

         long profileId,

         long productId,

         int visualMark,

         int storyMark,

         int gameplayMark,

         int soundMark,

         int spentTime,

         int rating,

         Timestamp rateDate

){
}
