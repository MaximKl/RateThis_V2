package com.ratethis.publicservice.dto.productdto;

import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.Genre;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public record ProductDTO(

        long id,

        String name,
        Date releaseDate,

        String picture,

        List<Country> countries,

        List<Genre> genres,

        String type,

        int rating,

        Timestamp addDate,

        long estimatesQuantity,
        long reviewQuantity,

        String ageRestriction

)  {





}
