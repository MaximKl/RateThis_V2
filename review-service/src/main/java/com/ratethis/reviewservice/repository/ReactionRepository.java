package com.ratethis.reviewservice.repository;

import com.ratethis.reviewservice.model.Reaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReactionRepository  extends CrudRepository<Reaction, Long> {

    List<Reaction> findAllByUserId(long userId);

    Reaction findReactionByUserIdAndReviewId(long userId, long reviewId);
    void deleteReactionByUserIdAndReviewId(long userId, long reviewId);

}
