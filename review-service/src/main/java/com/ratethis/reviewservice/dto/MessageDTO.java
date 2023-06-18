package com.ratethis.reviewservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDTO {

    @NotBlank(message = "Review can't be blank")
    @Length(max = 5_000, min = 20, message = "Minimum size of review is 20 symbols, maximum size - 5000 symbols")
    private String message;
}

