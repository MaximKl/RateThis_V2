package com.ratethis.publicservice.dto.mapper.usermap;

import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.dto.userdto.UserFullFilmDTO;
import com.ratethis.publicservice.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;


@Service
@AllArgsConstructor
public class UserFullFilmDTOMapper {


    private final ProductDeveloperDTOMapper devMapper;



    public UserFullFilmDTO apply(ProfileFilm profileFilm, ProductFilm productFilm) {
        String body = "";
        long id =0;
        int like = -1;
        int dislike = -1;
        Timestamp time = null;
        Timestamp editTime = null;
        boolean isEdit =false;

        if (profileFilm.getProfileEntity().getUserReviews().stream().anyMatch(rev -> rev.getProduct() == profileFilm.getProductId())) {
            UserReview review = profileFilm.getProfileEntity().getUserReviews().stream().filter(rev -> rev.getProduct() == profileFilm.getProductId()).findFirst().get();
            body = review.getBody();
            like = review.getLike();
            dislike = review.getDislike();
            time = review.getTime();
            editTime = review.getEditTime();
            isEdit = review.isEdit();
            id= review.getId();
        }

        return new UserFullFilmDTO(
                profileFilm.getProductId(),
                profileFilm.getProductEntity().getName(),
                profileFilm.getProductEntity().getDescription(),
                profileFilm.getProductEntity().getReleaseDate(),
                productPictureParser(profileFilm.getProductEntity().getPicture()),
                profileFilm.getProductEntity().getCountries().stream().map(Country::getName).toList(),
                profileFilm.getProductEntity().getGenres().stream().map(Genre::getName).toList(),
                profileFilm.getProductEntity().getAddDate(),
                profileFilm.getProductEntity().getType().getName(),
                profileFilm.getProductEntity().getAgeRestriction(),
                profileFilm.getProductEntity().getRating(),

                productFilm.getVisualMark(),
                productFilm.getStoryMark(),
                productFilm.getSoundMark(),
                productFilm.getActMark(),

                profileFilm.getRating(),
                productFilm.getTime(),
                profileFilm.getVisualMark(),
                profileFilm.getStoryMark(),
                profileFilm.getSoundMark(),
                profileFilm.getActMark(),

                profileFilm.getProductEntity().getEstimationQuantity(),
                profileFilm.getProductEntity().getReviewQuantity(),

                id,
                body,
                like,
                dislike,
                time,
                editTime,
                isEdit,
                profileFilm.getRateDate(),
                devMapper.apply(profileFilm.getProductEntity().getDevelopers())
        );
    }
}
