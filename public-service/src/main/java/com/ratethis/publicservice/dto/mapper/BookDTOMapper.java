package com.ratethis.publicservice.dto.mapper;

import com.ratethis.publicservice.dto.BookDTO;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;

@Service
@AllArgsConstructor
public class BookDTOMapper implements Function<ProductBook, BookDTO> {

    private final ProductDeveloperDTOMapper developerMapper;


    @Override
    public BookDTO apply(ProductBook productBook) {

        return new BookDTO(
                productBook.getId(),
                productBook.getProduct().getName(),
                productBook.getProduct().getDescription(),
                productBook.getProduct().getReleaseDate(),
                productPictureParser(productBook.getProduct().getPicture()),
                productBook.getProduct().getCountries(),
                productBook.getProduct().getGenres(),
                productBook.getProduct().getType().getName(),
                productBook.getProduct().getRating(),
                productBook.getProduct().getAddDate(),
                productBook.getStoryMark(),
                productBook.getProduct().getAgeRestriction(),
                productBook.getArtMark(),
                productBook.getInfoMark(),
                productBook.getSize(),
                productBook.getProduct().getEstimationQuantity(),
                productBook.getProduct().getReviewQuantity(),
                developerMapper.apply(productBook.getProduct().getDevelopers()));
    }
}
