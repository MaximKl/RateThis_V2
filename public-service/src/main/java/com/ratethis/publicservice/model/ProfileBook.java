package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "profile_book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ProfileBook.class)
public class ProfileBook implements Serializable {

    @Id
    @Column(name = "profile_id")
    private long profileId;
    @Id
    @Column(name = "book_id")
    private long productId;
    @ManyToOne
    @MapsId
    @JoinColumn(name = "profile_id")
    private UserProfile profileEntity;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "book_id")
    private Product productEntity;

    @Basic
    @Column(name = "book_story_mark")
    private int storyMark;
    @Basic
    @Column(name = "book_art_mark")
    private int artMark;
    @Basic
    @Column(name = "book_info_mark")
    private int infoMark;
    @Basic
    @Column(name = "book_rating")
    private int rating;
    @Basic
    @Column(name = "book_rate_date")
    private Timestamp rateDate;

}
