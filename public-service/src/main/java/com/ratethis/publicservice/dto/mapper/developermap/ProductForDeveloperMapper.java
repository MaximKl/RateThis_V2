package com.ratethis.publicservice.dto.mapper.developermap;

import com.ratethis.publicservice.dto.productdto.ProductDTO;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.productPictureParser;

@Service
public class ProductForDeveloperMapper implements Function<Map<DeveloperRole, Product>, Map<String, List<ProductDTO>>> {

    @Override
    public Map<String, List<ProductDTO>> apply(Map<DeveloperRole, Product> project) {

        Map<String, List<ProductDTO>> toReturn = new HashMap<>();
        for (Map.Entry<DeveloperRole, Product> data : project.entrySet()) {
            if (!toReturn.containsKey(data.getKey().getName())) {
                toReturn.put(data.getKey().getName(), new ArrayList<>());
            }
            toReturn.get(data.getKey().getName())
                    .add(new ProductDTO(
                            data.getValue().getId(),
                            data.getValue().getName(),
                            data.getValue().getReleaseDate(),
                            productPictureParser(data.getValue().getPicture()),
                            data.getValue().getCountries(),
                            data.getValue().getGenres(),
                            data.getValue().getType().getName(),
                            data.getValue().getRating(),
                            data.getValue().getAddDate(),
                            data.getValue().getEstimationQuantity(),
                            data.getValue().getReviewQuantity(),
                            data.getValue().getAgeRestriction())
                    );
        }
        return toReturn;
    }

}
