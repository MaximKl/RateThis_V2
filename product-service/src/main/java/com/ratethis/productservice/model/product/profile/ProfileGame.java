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
@Table(name = "profile_game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ProfileGameID.class)
public class ProfileGame {

    @Id
    @Column(name = "profile_id")
    private long profileId;
    @Id
    @Column(name = "game_id")
    private long productId;

    @Basic
    @Column(name = "game_visual_mark")
    @Max(value = 100, message = "Visual estimate must be less than 100")
    @Min(value = 1, message = "Visual estimate must be more than 1")
    private int visualMark;
    @Basic
    @Column(name = "game_story_mark")
    @Max(value = 100, message = "Story estimate must be less than 100")
    @Min(value = 1, message = "Story estimate must be more than 1")
    private int storyMark;
    @Basic
    @Column(name = "game_gameplay_mark")
    @Max(value = 100, message = "Gameplay estimate must be less than 100")
    @Min(value = 1, message = "Gameplay estimate must be more than 1")
    private int gameplayMark;
    @Basic
    @Column(name = "game_sound_mark")
    @Max(value = 100, message = "Sound estimate must be less than 100")
    @Min(value = 1, message = "Sound estimate must be more than 1")
    private int soundMark;
    @Basic
    @Column(name = "game_spent_time")
    @Max(value = 10_000, message = "Spent time estimate must be less than 10000")
    @Min(value = 1, message = "Spent time estimate must be more than 1")
    private int spentTime;
    @Basic
    @Column(name = "game_rating")
    @Max(value = 100, message = "Rating must be less than 100")
    @Min(value = 1, message = "Rating must be more than 1")
    private int rating;
    @Basic
    @Column(name = "game_rate_date")
    private Timestamp rateDate;
}
