package com.ratethis.publicservice.dto.mapper.usermap;

import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.userdto.UserFilmDTO;
import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.Genre;
import com.ratethis.publicservice.model.ProfileFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;

@Service
public class UserFilmDTOMapper implements Function<ProfileFilm, UserFilmDTO> {

    private final ProductDeveloperDTOMapper developerDTOMapper;

    @Autowired
    public UserFilmDTOMapper(ProductDeveloperDTOMapper developerDTOMapper) {
        this.developerDTOMapper = developerDTOMapper;
    }

    @Override
    public UserFilmDTO apply(ProfileFilm profileFilm) {
        return new UserFilmDTO(
                profileFilm.getProductId(),
                profileFilm.getProductEntity().getName(),
                profileFilm.getProductEntity().getReleaseDate(),
                productPictureParser(profileFilm.getProductEntity().getPicture()),
                profileFilm.getProductEntity().getCountries().stream().map(Country::getName).toList(),
                profileFilm.getProductEntity().getGenres().stream().map(Genre::getName).toList(),
                profileFilm.getProductEntity().getType().getName(),
                profileFilm.getProductEntity().getAgeRestriction(),
                profileFilm.getProductEntity().getRating(),
                profileFilm.getRating(),
                profileFilm.getVisualMark(),
                profileFilm.getStoryMark(),
                profileFilm.getSoundMark(),
                profileFilm.getActMark(),
                profileFilm.getRateDate()
        );
    }
}
