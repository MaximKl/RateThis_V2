package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.Developer;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer,Long> {
}
