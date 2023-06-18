package com.ratethis.productservice.dto.mapper;

import com.ratethis.productservice.constants.ProductTypes;
import com.ratethis.productservice.dto.ProductForSaveDTO;
import com.ratethis.productservice.model.product.product.Product;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ProductForSaveDTOMapper {


    public Product apply(ProductForSaveDTO product, Product oldProduct) {

        String productPicture = "";
        if (product.getPicture().startsWith("link:"))
            productPicture = product.getPicture();

        int type = 0;
        if (product.getType().equals("book"))
            type = ProductTypes.BOOK;
        if (product.getType().equals("game"))
            type = ProductTypes.GAME;
        if (product.getType().equals("film"))
            type = ProductTypes.FILM;


        return new Product(
                product.getId(),
                product.getName(),
                oldProduct != null ? oldProduct.getRating() : 0,
                Timestamp.valueOf(LocalDateTime.now()),
                product.getDescription(),
                product.getReleaseDate(),
                productPicture,
                product.getAgeRestriction(),
                oldProduct != null ? oldProduct.getReviewQuantity() : 0,
                oldProduct != null ? oldProduct.getEstimationQuantity() : 0,
                type
        );
    }


}
