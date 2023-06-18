package com.ratethis.reviewservice.model;

import com.ratethis.reviewservice.model.keys.ReactionID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "user_reaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ReactionID.class)
@DynamicUpdate(true)
public class Reaction {


    @Id
    @Column(name = "reaction_user")
    private long userId;
    @Id
    @Column(name = "reaction_review")
    private long reviewId;
    @Column(name = "reaction_is_Like")
    private boolean isLike;
    @Column(name = "reaction_is_dislike")
    private boolean isDislike;

}
