package com.ratethis.productservice.dto;


import java.sql.Timestamp;

public record ProfileBookDTO(
        int storyMark,

        int artMark,

        int infoMark,
        int rating,
        Timestamp rateDate){}
