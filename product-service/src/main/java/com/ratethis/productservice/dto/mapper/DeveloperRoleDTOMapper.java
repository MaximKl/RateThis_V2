package com.ratethis.productservice.dto.mapper;

import com.ratethis.productservice.dto.DeveloperDTO;
import com.ratethis.productservice.model.developer.DeveloperToRoleObjects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeveloperRoleDTOMapper implements Function<List<DeveloperToRoleObjects>, TreeMap<String, Set<DeveloperDTO>>> {

    private final DeveloperDTOMapper developerDTOMapper;

    @Override
    public TreeMap<String, Set<DeveloperDTO>> apply(List<DeveloperToRoleObjects> productDeveloper) {
        TreeMap<String, Set<DeveloperDTO>> developers = new TreeMap<>();
        Set<String> allRoles = new HashSet<>();
        productDeveloper.forEach(pd -> allRoles.add(pd.getRole().getName()));
        for (String role : allRoles) {
            developers.put(role,
                    productDeveloper.stream()
                            .filter(pd -> pd.getRole().getName().equals(role))
                            .map(developerDTOMapper)
                            .collect(Collectors.toSet()));
        }
        return developers;
    }
}
