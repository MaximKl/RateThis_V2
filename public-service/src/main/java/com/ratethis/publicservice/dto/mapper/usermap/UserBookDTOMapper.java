package com.ratethis.publicservice.dto.mapper.usermap;

import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.userdto.UserBookDTO;
import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.Genre;
import com.ratethis.publicservice.model.ProfileBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;

@Service
public class UserBookDTOMapper implements Function<ProfileBook, UserBookDTO> {

    private final ProductDeveloperDTOMapper developerDTOMapper;

    @Autowired
    public UserBookDTOMapper(ProductDeveloperDTOMapper developerDTOMapper) {
        this.developerDTOMapper = developerDTOMapper;
    }

    @Override
    public UserBookDTO apply(ProfileBook profileBook) {
        return new UserBookDTO(
                profileBook.getProductId(),
                profileBook.getProductEntity().getName(),
                profileBook.getProductEntity().getReleaseDate(),
                productPictureParser(profileBook.getProductEntity().getPicture()),
                profileBook.getProductEntity().getCountries().stream().map(Country::getName).toList(),
                profileBook.getProductEntity().getGenres().stream().map(Genre::getName).toList(),
                profileBook.getProductEntity().getType().getName(),
                profileBook.getProductEntity().getAgeRestriction(),
                profileBook.getProductEntity().getRating(),
                profileBook.getRating(),
                profileBook.getStoryMark(),
                profileBook.getArtMark(),
                profileBook.getInfoMark(),
                profileBook.getRateDate()
        );
    }
}
