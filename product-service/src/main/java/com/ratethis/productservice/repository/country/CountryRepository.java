package com.ratethis.productservice.repository.country;

import com.ratethis.productservice.model.country.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Integer> {
    List<Country> findAllBy();
}
