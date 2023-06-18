package com.ratethis.reviewservice.dto;

import java.sql.Timestamp;

public record ReviewDTO (

       long id,
       String body,
       int like,
       int dislike,
       Timestamp time,
       long userId,
       long productId
){
}
