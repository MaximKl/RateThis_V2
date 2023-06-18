package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "profile_film")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ProfileFilm.class)
public class ProfileFilm implements Serializable {

    @Id
    @Column(name = "profile_id")
    private long profileId;
    @Id
    @Column(name = "film_id")
    private long productId;
    @ManyToOne
    @MapsId
    @JoinColumn(name = "profile_id")
    private UserProfile profileEntity;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "film_id")
    private Product productEntity;

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
    @Column(name = "film_rating")
    private int rating;
    @Basic
    @Column(name = "film_rate_date")
    private Timestamp rateDate;


}
