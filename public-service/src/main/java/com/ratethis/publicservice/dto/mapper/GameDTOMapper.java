package com.ratethis.publicservice.dto.mapper;

import com.ratethis.publicservice.dto.GameDTO;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.GameType;
import com.ratethis.publicservice.model.Genre;
import com.ratethis.publicservice.model.ProductGame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;

@Service
@AllArgsConstructor
public class GameDTOMapper implements Function<ProductGame, GameDTO> {


    private final ProductDeveloperDTOMapper developerMapper;


    @Override
    public GameDTO apply(ProductGame productGame) {
        return new GameDTO(
                productGame.getId(),
                productGame.getProduct().getName(),
                productGame.getProduct().getDescription(),
                productGame.getProduct().getReleaseDate(),
                productPictureParser(productGame.getProduct().getPicture()),
                productGame.getProduct().getCountries(),
                productGame.getProduct().getGenres(),
                productGame.getProduct().getType().getName(),
                productGame.getProduct().getRating(),
                productGame.getProduct().getAddDate(),
                productGame.getProduct().getAgeRestriction(),
                productGame.getVisualMark(),
                productGame.getStoryMark(),
                productGame.getGameplayMark(),
                productGame.getSoundMark(),
                productGame.getSpentTime(),
                productGame.getGameTypes(),
                productGame.getProduct().getEstimationQuantity(),
                productGame.getProduct().getReviewQuantity(),
                developerMapper.apply(productGame.getProduct().getDevelopers())
        );
    }
}
