package com.ratethis.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductForSaveDTO {

    @NotNull(message = "Product id can't be null")
    private long id;

    @Length(max = 2000, min = 1, message = "Product name length must be from 1 to 2000 symbols")
    @NotBlank(message = "Product name can't be empty")
    private String name;

    @Length(max = 5000, min = 60, message = "Product description length must be from 60 to 5000 symbols")
    @NotBlank(message = "Product description can't be empty")
    private String description;

    @PastOrPresent(message = "Product release date can't be in future")
    private Date releaseDate;

    private String picture;

    @Length(max = 3, min = 2, message = "Product age restriction length must be from 2 to 3 symbols")
    @NotBlank(message = "Product age restriction can't be empty")
    private String ageRestriction;

    private String size;

    private String type;
    @NotEmpty(message = "Product genres can't be empty")
    private List<Long> genres;

    @NotEmpty(message = "Product countries can't be empty")
    private List<Integer> countries;

    private List<Integer> gameTypes;

    @NotEmpty(message = "Product developers can't be empty")
    private List<List<Object>> developers;

}
