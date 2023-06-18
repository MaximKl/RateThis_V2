package com.ratethis.productservice.repository.developer;

import com.ratethis.productservice.model.developer.Developer;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer,Integer> {
    void deleteDeveloperById(int id);
}
