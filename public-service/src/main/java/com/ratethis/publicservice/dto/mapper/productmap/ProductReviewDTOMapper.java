package com.ratethis.publicservice.dto.mapper.productmap;

import com.ratethis.publicservice.constants.Constants;
import com.ratethis.publicservice.dto.productdto.ProductReviewDTO;
import com.ratethis.publicservice.model.UserReview;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductReviewDTOMapper implements Function<UserReview, ProductReviewDTO> {
    @Override
    public ProductReviewDTO apply(UserReview userReview) {
        return new ProductReviewDTO(
                userReview.getId(),
                userReview.getBody(),
                userReview.getLike(),
                userReview.getDislike(),
                userReview.getTime(),
                userReview.getUser().getNick(),
                Constants.userAvatarParser(userReview.getUser().getImage()),
                userReview.getUser().getUserColor().getName(),
                userReview.getUser().getUserRole().getName(),
                userReview.isEdit(),
                userReview.getEditTime()
        );
    }
}
