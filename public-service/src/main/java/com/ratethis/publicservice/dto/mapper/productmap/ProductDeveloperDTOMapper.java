package com.ratethis.publicservice.dto.mapper.productmap;

import com.ratethis.publicservice.dto.developerdto.DeveloperDTO;
import com.ratethis.publicservice.model.Developer;
import com.ratethis.publicservice.model.DeveloperRole;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.developerPictureParser;

@Service
public class ProductDeveloperDTOMapper implements Function<Map<DeveloperRole, Developer>, Map<String, List<DeveloperDTO>>> {
    @Override
    public Map<String, List<DeveloperDTO>> apply(Map<DeveloperRole, Developer> developer) {
        Map<String, List<DeveloperDTO>> toReturn = new HashMap<>();
        for (Map.Entry<DeveloperRole, Developer> data : developer.entrySet()) {
            if (!toReturn.containsKey(data.getKey().getName())) {
                toReturn.put(data.getKey().getName(), new ArrayList<>());
            }
            toReturn.get(data.getKey().getName())
                    .add(new DeveloperDTO(
                            data.getValue().getId(),
                            data.getValue().getName(),
                            developerPictureParser(data.getValue().getPhoto()),
                            data.getValue().getDescription(),
                            data.getValue().getBirthday(),
                            data.getValue().getCountry().getName()));
        }
        return toReturn;
    }
}
