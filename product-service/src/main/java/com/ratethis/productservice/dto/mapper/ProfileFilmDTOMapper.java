package com.ratethis.productservice.dto.mapper;

import com.ratethis.productservice.dto.ProfileFilmDTO;
import com.ratethis.productservice.model.product.profile.ProfileFilm;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfileFilmDTOMapper implements Function<ProfileFilm, ProfileFilmDTO> {

    @Override
    public ProfileFilmDTO apply(ProfileFilm profileFilm) {
        return new ProfileFilmDTO(
                profileFilm.getProfileId(),
                profileFilm.getProductId(),
                profileFilm.getVisualMark(),
                profileFilm.getStoryMark(),
                profileFilm.getSoundMark(),
                profileFilm.getActMark(),
                profileFilm.getRating(),
                profileFilm.getRateDate()
        );
    }
}
