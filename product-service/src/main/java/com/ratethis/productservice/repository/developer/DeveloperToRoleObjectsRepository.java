package com.ratethis.productservice.repository.developer;

import com.ratethis.productservice.model.developer.DeveloperToRoleObjects;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperToRoleObjectsRepository extends CrudRepository<DeveloperToRoleObjects, Long> {

    List<DeveloperToRoleObjects> findAllBy();

    List<DeveloperToRoleObjects> findAllByDeveloperId(int developerId);

}
