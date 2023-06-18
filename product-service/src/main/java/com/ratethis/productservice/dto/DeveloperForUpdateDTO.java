package com.ratethis.productservice.dto;

import com.ratethis.productservice.model.country.Country;
import com.ratethis.productservice.model.developer.DeveloperRole;

import java.util.Date;
import java.util.List;

public record DeveloperForUpdateDTO(
        int id,

        String name,

        String description,

        String photo,

        Date birthday,

        Country country,
        List<DeveloperRole> roles

) {
}
