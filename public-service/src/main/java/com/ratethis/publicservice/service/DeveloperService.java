package com.ratethis.publicservice.service;

import com.ratethis.publicservice.constants.Constants;
import com.ratethis.publicservice.dto.developerdto.UniqueDeveloperDTO;
import com.ratethis.publicservice.dto.mapper.developermap.UniqueDeveloperDTOMapper;
import com.ratethis.publicservice.exception.ProductNotFoundException;
import com.ratethis.publicservice.repository.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ratethis.publicservice.constants.ProductTypes.DEVELOPER_NAME;

@Service
@AllArgsConstructor
public class DeveloperService {

    private final DeveloperRepository repository;

    private final UniqueDeveloperDTOMapper mapper;


    public UniqueDeveloperDTO getDeveloper(String id) {
        if (Constants.isNumberCheck(id)) {
            return repository.findById(Long.parseLong(id)).map(mapper)
                    .orElseThrow(() -> new ProductNotFoundException(DEVELOPER_NAME, id));
        }
        throw new ProductNotFoundException(DEVELOPER_NAME, id);
    }

}
