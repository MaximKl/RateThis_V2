package com.ratethis.publicservice.dto.mapper.productmap;

import com.ratethis.publicservice.dto.productdto.ProductDTO;
import com.ratethis.publicservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {



    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getReleaseDate(),
                productPictureParser(product.getPicture()),
                product.getCountries(),
                product.getGenres(),
                product.getType().getName(),
                product.getRating(),
                product.getAddDate(),
                product.getEstimationQuantity(),
                product.getReviewQuantity(),
                product.getAgeRestriction());
    }
}
