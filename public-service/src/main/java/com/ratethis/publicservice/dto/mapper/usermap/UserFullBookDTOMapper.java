package com.ratethis.publicservice.dto.mapper.usermap;

import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.dto.userdto.UserFullBookDTO;
import com.ratethis.publicservice.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;

@Service
@AllArgsConstructor
public class UserFullBookDTOMapper {

    private final ProductDeveloperDTOMapper devMapper;


    public UserFullBookDTO apply(ProfileBook profileBook, ProductBook productBook) {
        String body = "";
        long id =0;
        int like = -1;
        int dislike = -1;
        Timestamp time = null;
        Timestamp editTime = null;
        boolean isEdit = false;

        if (profileBook.getProfileEntity().getUserReviews().stream().anyMatch(rev -> rev.getProduct() == profileBook.getProductId())) {
            UserReview review = profileBook.getProfileEntity().getUserReviews().stream().filter(rev -> rev.getProduct() == profileBook.getProductId()).findFirst().get();
            body = review.getBody();
            like = review.getLike();
            dislike = review.getDislike();
            time = review.getTime();
            editTime = review.getEditTime();
            isEdit = review.isEdit();
            id= review.getId();
        }

        return new UserFullBookDTO(
                profileBook.getProductId(),
                profileBook.getProductEntity().getName(),
                profileBook.getProductEntity().getDescription(),
                profileBook.getProductEntity().getReleaseDate(),
                productPictureParser(profileBook.getProductEntity().getPicture()),
                profileBook.getProductEntity().getCountries().stream().map(Country::getName).toList(),
                profileBook.getProductEntity().getGenres().stream().map(Genre::getName).toList(),
                profileBook.getProductEntity().getAddDate(),
                profileBook.getProductEntity().getType().getName(),
                profileBook.getProductEntity().getAgeRestriction(),
                profileBook.getProductEntity().getRating(),

                productBook.getStoryMark(),
                productBook.getArtMark(),
                productBook.getInfoMark(),
                productBook.getSize(),

                profileBook.getRating(),
                profileBook.getStoryMark(),
                profileBook.getArtMark(),
                profileBook.getInfoMark(),

                profileBook.getProductEntity().getEstimationQuantity(),
                profileBook.getProductEntity().getReviewQuantity(),

                id,
                body,
                like,
                dislike,
                time,
                editTime,
                isEdit,

                profileBook.getRateDate(),
                devMapper.apply(profileBook.getProductEntity().getDevelopers())
        );
    }
}
