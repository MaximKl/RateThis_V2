package com.ratethis.publicservice.dto.mapper;

import com.ratethis.publicservice.dto.FilmDTO;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.Genre;
import com.ratethis.publicservice.model.ProductFilm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;


@Service
@AllArgsConstructor
public class FilmDTOMapper implements Function<ProductFilm, FilmDTO> {

    private final ProductDeveloperDTOMapper developerMapper;


    @Override
    public FilmDTO apply(ProductFilm productFilm) {
        return new FilmDTO(
                productFilm.getId(),
                productFilm.getProduct().getName(),
                productFilm.getProduct().getDescription(),
                productFilm.getProduct().getReleaseDate(),
                productPictureParser(productFilm.getProduct().getPicture()),
                productFilm.getProduct().getCountries(),
                productFilm.getProduct().getGenres(),
                productFilm.getProduct().getType().getName(),
                productFilm.getProduct().getRating(),
                productFilm.getProduct().getAddDate(),
                productFilm.getProduct().getAgeRestriction(),
                productFilm.getTime(),
                productFilm.getVisualMark(),
                productFilm.getStoryMark(),
                productFilm.getSoundMark(),
                productFilm.getActMark(),
                productFilm.getProduct().getEstimationQuantity(),
                productFilm.getProduct().getReviewQuantity(),
                developerMapper.apply(productFilm.getProduct().getDevelopers())
        );
    }
}
