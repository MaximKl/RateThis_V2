package com.ratethis.productservice.repository.developer;

import com.ratethis.productservice.model.developer.DeveloperToRole;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperToRoleRepository extends CrudRepository<DeveloperToRole, Integer> {
    void deleteDeveloperToRoleByDeveloper(int developer);
}
