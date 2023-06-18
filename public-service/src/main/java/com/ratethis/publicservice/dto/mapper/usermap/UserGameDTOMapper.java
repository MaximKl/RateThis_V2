package com.ratethis.publicservice.dto.mapper.usermap;

import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.userdto.UserGameDTO;
import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.Genre;
import com.ratethis.publicservice.model.ProfileGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;

@Service
public class UserGameDTOMapper implements Function<ProfileGame, UserGameDTO> {

    private final ProductDeveloperDTOMapper developerDTOMapper;

    @Autowired
    public UserGameDTOMapper(ProductDeveloperDTOMapper developerDTOMapper) {
        this.developerDTOMapper = developerDTOMapper;
    }

    @Override
    public UserGameDTO apply(ProfileGame profileGame) {
        return new UserGameDTO(
                profileGame.getProductId(),
                profileGame.getProductEntity().getName(),
                profileGame.getProductEntity().getReleaseDate(),
                productPictureParser(profileGame.getProductEntity().getPicture()),
                profileGame.getProductEntity().getCountries().stream().map(Country::getName).toList(),
                profileGame.getProductEntity().getGenres().stream().map(Genre::getName).toList(),
                profileGame.getProductEntity().getType().getName(),
                profileGame.getProductEntity().getAgeRestriction(),
                profileGame.getProductEntity().getRating(),
                profileGame.getRating(),
                profileGame.getVisualMark(),
                profileGame.getStoryMark(),
                profileGame.getGameplayMark(),
                profileGame.getSoundMark(),
                profileGame.getSpentTime(),
                profileGame.getRateDate()
        );
    }
}
