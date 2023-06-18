package com.ratethis.productservice.dto.mapper;

import com.ratethis.productservice.dto.ProfileGameDTO;
import com.ratethis.productservice.model.product.profile.ProfileGame;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfileGameDTOMapper implements Function<ProfileGame, ProfileGameDTO> {
    @Override
    public ProfileGameDTO apply(ProfileGame profileGame) {
        return new ProfileGameDTO(
                profileGame.getProfileId(),
                profileGame.getProductId(),
                profileGame.getVisualMark(),
                profileGame.getStoryMark(),
                profileGame.getGameplayMark(),
                profileGame.getSoundMark(),
                profileGame.getSpentTime(),
                profileGame.getRating(),
                profileGame.getRateDate()
        );
    }
}
