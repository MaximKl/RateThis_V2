package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "profile_game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ProfileGame.class)
public class ProfileGame implements Serializable {

    @Id
    @Column(name = "profile_id")
    private long profileId;
    @Id
    @Column(name = "game_id")
    private long productId;
    @ManyToOne
    @MapsId
    @JoinColumn(name = "profile_id")
    private UserProfile profileEntity;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "game_id")
    private Product productEntity;

    @Basic
    @Column(name = "game_visual_mark")
    private int visualMark;
    @Basic
    @Column(name = "game_story_mark")
    private int storyMark;
    @Basic
    @Column(name = "game_gameplay_mark")
    private int gameplayMark;
    @Basic
    @Column(name = "game_sound_mark")
    private int soundMark;
    @Basic
    @Column(name = "game_spent_time")
    private int spentTime;
    @Basic
    @Column(name = "game_rating")
    private int rating;
    @Basic
    @Column(name = "game_rate_date")
    private Timestamp rateDate;
}
