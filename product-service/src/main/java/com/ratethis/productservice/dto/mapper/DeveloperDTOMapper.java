package com.ratethis.productservice.dto.mapper;

import com.ratethis.productservice.dto.DeveloperDTO;
import com.ratethis.productservice.model.developer.DeveloperToRoleObjects;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DeveloperDTOMapper implements Function<DeveloperToRoleObjects, DeveloperDTO> {

    @Override
    public DeveloperDTO apply(DeveloperToRoleObjects productDeveloper) {
        return new DeveloperDTO(
                productDeveloper.getDeveloper().getId(),
                productDeveloper.getDeveloper().getName(),
                productDeveloper.getDeveloper().getBirthday()
        );
    }
}
