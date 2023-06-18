package com.ratethis.productservice.repository.developer;

import com.ratethis.productservice.model.developer.DeveloperRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRoleRepository extends CrudRepository<DeveloperRole, Long> {
    Optional<DeveloperRole> findDeveloperRoleByName(String name);
    List<DeveloperRole> findAllBy();
}
