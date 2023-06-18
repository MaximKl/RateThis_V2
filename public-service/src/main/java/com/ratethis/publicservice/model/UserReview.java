package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "user_review")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReview {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_id")
    private long id;
    @Basic
    @Column(name = "review_body")
    private String body;
    @Basic
    @Column(name = "review_like")
    private int like;
    @Basic
    @Column(name = "review_dislike")
    private int dislike;
    @Basic
    @Column(name = "review_time")
    private Timestamp time;
    @Basic
    @Column(name = "review_is_edit")
    private boolean isEdit;

    @Basic
    @Column(name = "review_edit_time")
    private Timestamp editTime;

    @ManyToOne
    @JoinColumn(name = "review_by")
    private UserProfile user;
    @Basic
    @Column(name = "review_for")
    private long product;

}
