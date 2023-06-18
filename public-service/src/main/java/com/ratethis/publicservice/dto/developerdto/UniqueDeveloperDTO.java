package com.ratethis.publicservice.dto.developerdto;

import com.ratethis.publicservice.dto.productdto.ProductDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public record UniqueDeveloperDTO(
        long id,
        String name,
                                 String photo,

                                 String description,

                                 Date birthday,

                                 String country,

                                 Map<String, List<ProductDTO>> projects

) {
}
