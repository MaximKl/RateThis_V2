package com.ratethis.reviewservice.repository;

import com.ratethis.reviewservice.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends CrudRepository<Review,Long> {

    Optional<Review> findReviewByUserIdAndProductId(long userId, long productId);
    void deleteReviewByUserIdAndProductId(long userId, long productId);

    @Query(value = "call is_product_exist(:id)", nativeQuery = true)
    byte isProductExist(@Param("id") long id);
}
