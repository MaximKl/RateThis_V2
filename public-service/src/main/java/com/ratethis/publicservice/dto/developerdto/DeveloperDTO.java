package com.ratethis.publicservice.dto.developerdto;


import java.util.Date;

public record DeveloperDTO(
        long id,
        String name,
        String photo,

        String description,

        Date birthday,

        String country) {
}
