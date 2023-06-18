package com.ratethis.productservice.dto.mapper;

import com.ratethis.productservice.dto.DeveloperForSaveDTO;
import com.ratethis.productservice.model.developer.Developer;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class DeveloperForSaveDTOMapper implements Function<DeveloperForSaveDTO, Developer> {
    @Override
    public Developer apply(DeveloperForSaveDTO developer) {
        return new Developer(
                developer.getId(),
                developer.getName(),
                developer.getPhoto().startsWith("link:") ? developer.getPhoto() : "",
                developer.getDescription(),
                developer.getBirthday(),
                developer.getCountry()
        );
    }
}
