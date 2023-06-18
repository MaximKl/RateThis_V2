package com.ratethis.publicservice.dto.productdto;

import java.sql.Date;

public record MainPageProductDTO(long id,
                                 String name,
                                 Date releaseDate,
                                 long estimatesQuantity,
                                 long reviewQuantity,
                                 String picture,
                                 int rating,
                                 String type,
                                 String ageRestriction) {
}
