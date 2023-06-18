package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_film")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductFilm {

    @Id
    private long id;
    @MapsId
    @OneToOne
    @JoinColumn(name="film_id")
    private Product product;
    @Basic
    @Column(name = "film_time")
    private int time;
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

}
