package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.UserReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<UserReview, Long> {
    List<UserReview> findAllByProduct(long productId);
}
