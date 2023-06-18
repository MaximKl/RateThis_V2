package com.ratethis.publicservice.dto.userdto;

import java.sql.Date;
import java.sql.Timestamp;

public record ProfileDTO(
        long id,
        String nick,
        String description,

         Timestamp regDate,

         Date birthday,

         int report,

         String image,

         String role,

         String color,

        long friendQuantity

) {
}
