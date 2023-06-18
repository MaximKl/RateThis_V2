package com.ratethis.productservice.model.genre;

import com.ratethis.productservice.model.genre.GenreKeysID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_genre")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(GenreKeysID.class)
@DynamicUpdate(value = true)
public class GenreKeys {

    @Id
    @Column(name = "product_id")
    private long productId;
    @Id
    @Column(name = "genre_id")
    private long genreId;

}
