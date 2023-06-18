package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductGame {

    @Id
    private long id;
    @MapsId
    @OneToOne
    @JoinColumn(name="game_id")
    private Product product;

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

    @ManyToMany
    @JoinTable(name = "game_to_type", inverseJoinColumns = {@JoinColumn(name = "type_id")}, joinColumns = {@JoinColumn(name = "game_id")})
    private List<GameType> gameTypes;

}
