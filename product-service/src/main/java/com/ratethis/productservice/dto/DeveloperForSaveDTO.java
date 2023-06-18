package com.ratethis.productservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeveloperForSaveDTO {

    @NotNull(message = "Developer must have an id")
    private int id;

    @Length(max = 500, min = 2, message = "Developer name length must be from 2 to 500 symbols")
    @NotBlank(message = "Developer name can't be empty")
    private String name;

    @Length(max = 5000, min = 20, message = "Developer description length must be from 20 to 5000 symbols")
    @NotBlank(message = "Developer description can't be empty")
    private String description;

    private String photo;

    @Past(message = "Developer creation date can't be in future")
    private Date birthday;

    @Min(1)
    private int country;

    @NotEmpty(message = "Roles can't be empty")
    private List<Integer> roles;

}
