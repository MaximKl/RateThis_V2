package com.ratethis.reviewservice.dto.mapper;

import com.ratethis.reviewservice.dto.ReviewDTO;
import com.ratethis.reviewservice.model.Review;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReviewDTOMapper implements Function<Review, ReviewDTO> {
    @Override
    public ReviewDTO apply(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getBody(),
                review.getLike(),
                review.getDislike(),
                review.getTime(),
                review.getUserId(),
                review.getProductId()
        );
    }
}
