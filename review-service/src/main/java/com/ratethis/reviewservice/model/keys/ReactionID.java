package com.ratethis.reviewservice.model.keys;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReactionID implements Serializable {

    private long userId;
    private long reviewId;
}
