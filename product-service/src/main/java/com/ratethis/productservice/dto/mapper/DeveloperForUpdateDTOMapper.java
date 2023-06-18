package com.ratethis.productservice.dto.mapper;

import com.ratethis.productservice.dto.DeveloperForUpdateDTO;
import com.ratethis.productservice.model.developer.DeveloperForUpdate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.ratethis.productservice.constants.Constants.developerPictureParser;

@Service
public class DeveloperForUpdateDTOMapper implements Function<DeveloperForUpdate, DeveloperForUpdateDTO> {
    @Override
    public DeveloperForUpdateDTO apply(DeveloperForUpdate developerForUpdate) {
        return new DeveloperForUpdateDTO(
                developerForUpdate.getId(),
                developerForUpdate.getName(),
                developerForUpdate.getDescription(),
                developerPictureParser(developerForUpdate.getPhoto()),
                developerForUpdate.getBirthday(),
                developerForUpdate.getCountry(),
                developerForUpdate.getRoles()
        );
    }
}
