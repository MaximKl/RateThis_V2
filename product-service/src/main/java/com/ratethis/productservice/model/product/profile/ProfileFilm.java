package com.ratethis.productservice.model.product.profile;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "profile_film")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ProfileFilmID.class)
public class ProfileFilm {

    @Id
    @Column(name = "profile_id")
    private long profileId;
    @Id
    @Column(name = "film_id")
    private long productId;

    @Basic
    @Column(name = "film_visual_mark")
    @Max(value = 100, message = "Visual estimate must be less than 100")
    @Min(value = 1, message = "Visual estimate must be more than 1")
    private int visualMark;
    @Basic
    @Column(name = "film_story_mark")
    @Max(value = 100, message = "Story estimate must be less than 100")
    @Min(value = 1, message = "Story estimate must be more than 1")
    private int storyMark;
    @Basic
    @Column(name = "film_sound_mark")
    @Max(value = 100, message = "Sound estimate must be less than 100")
    @Min(value = 1, message = "Sound estimate must be more than 1")
    private int soundMark;
    @Basic
    @Column(name = "film_act_mark")
    @Max(value = 100, message = "Act estimate must be less than 100")
    @Min(value = 1, message = "Act estimate must be more than 1")
    private int actMark;
    @Basic
    @Column(name = "film_rating")
    @Max(value = 100, message = "Rating must be less than 100")
    @Min(value = 1, message = "Rating must be more than 1")
    private int rating;
    @Basic
    @Column(name = "film_rate_date")
    private Timestamp rateDate;


}
