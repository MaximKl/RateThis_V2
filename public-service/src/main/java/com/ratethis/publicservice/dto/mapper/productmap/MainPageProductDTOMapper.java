package com.ratethis.publicservice.dto.mapper.productmap;

import com.ratethis.publicservice.dto.productdto.MainPageProductDTO;
import com.ratethis.publicservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;

@Service
public class MainPageProductDTOMapper implements Function<Product, MainPageProductDTO> {
    @Override
    public MainPageProductDTO apply(Product product) {
        return new MainPageProductDTO(product.getId(),
                product.getName(),
                product.getReleaseDate(),
                product.getEstimationQuantity(),
                product.getReviewQuantity(),
                productPictureParser(product.getPicture()),
                product.getRating(),
                product.getType().getName(),
                product.getAgeRestriction());
    }
}
