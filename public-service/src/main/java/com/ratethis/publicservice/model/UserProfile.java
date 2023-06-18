package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfile {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "profile_id")
    private long id;
    @Basic
    @Column(name = "profile_nick")
    private String nick;
    @Basic
    @Column(name = "profile_password")
    private String password;
    @Basic
    @Column(name = "profile_email")
    private String email;
    @Basic
    @Column(name = "profile_desc")
    private String desc;
    @Basic
    @Column(name = "profile_reg_date")
    private Timestamp regDate;
    @Basic
    @Column(name = "profile_birthday")
    private Date birthday;
    @Basic
    @Column(name = "profile_report")
    private int report;
    @Basic
    @Column(name = "profile_image")
    private String image;

    @OneToMany
    @JoinColumn(name = "review_by")
    private List<UserReview> userReviews;

    @ManyToOne
    @JoinColumn(name = "profile_role")
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "profile_color")
    private UserColor userColor;

    @OneToMany
    @JoinColumn(name = "profile_id")
    private List<ProfileFilm> films;

    @OneToMany
    @JoinColumn(name = "profile_id")
    private List<ProfileGame> games;

    @OneToMany
    @JoinColumn(name = "profile_id")
    private List<ProfileBook> books;

    @OneToMany
    @JoinColumn( name = "friend_one")
    private List<UserFriend> friendsOne;

    @OneToMany
    @JoinColumn( name = "friend_two")
    private List<UserFriend> friendsTwo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
