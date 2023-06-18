package com.ratethis.reviewservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

@Entity
@Table(name = "user_review")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_id")
    private long id;
    @Basic
    @Column(name = "review_body")
    @NotBlank(message = "Review can't be blank")
    @Length(max = 5_000, min = 20, message = "Minimum size of review is 20 symbols, maximum size - 5000 symbols")
    private String body;
    @Basic
    @Column(name = "review_like")
    @Min(value = 0, message = "Reaction can't be less than 0")
    private int like;
    @Basic
    @Column(name = "review_dislike")
    @Min(value = 0, message = "Reaction can't be less than 0")
    private int dislike;
    @Basic
    @Column(name = "review_time")
    private Timestamp time;
    @Basic
    @Column(name = "review_edit_time")
    private Timestamp editTime;
    @Basic
    @Column(name = "review_is_edit")
    private boolean isEdit;
    @Basic
    @Column(name = "review_by")
    private long userId;
    @Basic
    @Column(name = "review_for")
    private long productId;

}