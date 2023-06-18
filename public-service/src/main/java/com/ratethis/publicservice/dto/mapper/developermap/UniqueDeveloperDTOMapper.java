package com.ratethis.publicservice.dto.mapper.developermap;

import com.ratethis.publicservice.dto.developerdto.UniqueDeveloperDTO;
import com.ratethis.publicservice.model.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.ratethis.publicservice.constants.Constants.developerPictureParser;

@Service
public class UniqueDeveloperDTOMapper implements Function<Developer, UniqueDeveloperDTO> {

    private final ProductForDeveloperMapper mapper;

    @Autowired
    public UniqueDeveloperDTOMapper(ProductForDeveloperMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UniqueDeveloperDTO apply(Developer developer) {
        return new UniqueDeveloperDTO(
                developer.getId(),
                developer.getName(),
                developerPictureParser(developer.getPhoto()),
                developer.getDescription(),
                developer.getBirthday(),
                developer.getCountry().getName(),
                mapper.apply(developer.getProjects())
        );
    }
}
