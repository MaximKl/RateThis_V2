package com.ratethis.productservice.model.genre;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GenreKeysID implements Serializable {
    private long productId;
    private long genreId;
}
