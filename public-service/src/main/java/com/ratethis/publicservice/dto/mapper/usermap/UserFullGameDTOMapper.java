package com.ratethis.publicservice.dto.mapper.usermap;

import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.dto.userdto.UserFullFilmDTO;
import com.ratethis.publicservice.dto.userdto.UserFullGameDTO;
import com.ratethis.publicservice.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;


@Service
@AllArgsConstructor
public class UserFullGameDTOMapper{


    private final ProductDeveloperDTOMapper devMapper;



    public UserFullGameDTO apply(ProfileGame profileGame, ProductGame productGame) {
        String body = "";
        long id =0;
        int like = -1;
        int dislike = -1;
        Timestamp time = null;
        Timestamp editTime = null;
        boolean isEdit = false;

        if (profileGame.getProfileEntity().getUserReviews().stream().anyMatch(rev -> rev.getProduct() == profileGame.getProductId())) {
            UserReview review = profileGame.getProfileEntity().getUserReviews().stream().filter(rev -> rev.getProduct() == profileGame.getProductId()).findFirst().get();
            body = review.getBody();
            like = review.getLike();
            dislike = review.getDislike();
            time = review.getTime();
            editTime = review.getEditTime();
            isEdit = review.isEdit();
            id= review.getId();
        }

        return new UserFullGameDTO(
                profileGame.getProductId(),
                profileGame.getProductEntity().getName(),
                profileGame.getProductEntity().getDescription(),
                profileGame.getProductEntity().getReleaseDate(),
                productPictureParser(profileGame.getProductEntity().getPicture()),
                profileGame.getProductEntity().getCountries().stream().map(Country::getName).toList(),
                profileGame.getProductEntity().getGenres().stream().map(Genre::getName).toList(),
                profileGame.getProductEntity().getAddDate(),
                profileGame.getProductEntity().getType().getName(),
                profileGame.getProductEntity().getAgeRestriction(),
                productGame.getGameTypes().stream().map(GameType::getName).toList(),
                profileGame.getProductEntity().getRating(),

                productGame.getVisualMark(),
                productGame.getStoryMark(),
                productGame.getGameplayMark(),
                productGame.getSoundMark(),
                productGame.getSpentTime(),

                profileGame.getRating(),
                profileGame.getVisualMark(),
                profileGame.getStoryMark(),
                profileGame.getGameplayMark(),
                profileGame.getSoundMark(),
                profileGame.getSpentTime(),

                profileGame.getProductEntity().getEstimationQuantity(),
                profileGame.getProductEntity().getReviewQuantity(),

                id,
                body,
                like,
                dislike,
                time,
                editTime,
                isEdit,
                profileGame.getRateDate(),
                devMapper.apply(profileGame.getProductEntity().getDevelopers())
        );
    }
}
