package com.ratethis.productservice.model.product.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_film")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate(true)
public class ProductFilm {

    @Id
    @Column(name = "film_id")
    private long filmId;
    @Basic
    @Column(name = "film_visual_mark")
    private int visualMark;
    @Basic
    @Column(name = "film_story_mark")
    private int storyMark;
    @Basic
    @Column(name = "film_sound_mark")
    private int soundMark;
    @Basic
    @Column(name = "film_act_mark")
    private int actMark;
    @Basic
    @Column(name = "film_time")
    private int time;

}
