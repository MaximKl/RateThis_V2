package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "user_friend")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserFriend {
    @Id
    @Column(name = "friend_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "friend_one")
    private UserProfile friendOne;

    @ManyToOne
    @JoinColumn(name = "friend_two")
    private UserProfile friendTwo;

    @Basic
    @Column(name = "friend_is_approve")
    private boolean friendIsApprove;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFriend that = (UserFriend) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
