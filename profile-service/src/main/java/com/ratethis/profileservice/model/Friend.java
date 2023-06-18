package com.ratethis.profileservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_friend")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Friend {
    @Id
    @Column(name = "friend_id")
    private long id;

    @Basic
    @Column(name = "friend_one")
    private long friendOne;

    @Basic
    @Column(name = "friend_two")
    private long friendTwo;

    @Basic
    @Column(name = "friend_is_approve")
    private boolean friendIsApprove;

}
