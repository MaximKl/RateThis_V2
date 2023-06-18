package com.ratethis.productservice.model.product.profile;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Entity
@Table(name = "profile_book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ProfileBookID.class)
@DynamicUpdate(true)
public class ProfileBook {

    @Id
    @Column(name = "profile_id")
    private long profileId;
    @Id
    @Column(name = "book_id")
    private long productId;

    @Basic
    @Column(name = "book_story_mark")
    @Max(value = 100, message = "Story estimate must be less than 100")
    @Min(value = 1, message = "Story estimate must be more than 1")
    private int storyMark;
    @Basic
    @Column(name = "book_art_mark")
    @Max(value = 100, message = "Art estimate must be less than 100")
    @Min(value = 1, message = "Art estimate must be more than 1")
    private int artMark;
    @Basic
    @Column(name = "book_info_mark")
    @Max(value = 100, message = "Information estimate must be less than 100")
    @Min(value = 1, message = "Information estimate must be more than 1")
    private int infoMark;
    @Basic
    @Column(name = "book_rating")
    @Max(value = 100, message = "Rating must be less than 100")
    @Min(value = 1, message = "Rating must be more than 1")
    private int rating;
    @Basic
    @Column(name = "book_rate_date")
    private Timestamp rateDate;
}
