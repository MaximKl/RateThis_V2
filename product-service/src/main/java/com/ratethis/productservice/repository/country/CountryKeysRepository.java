package com.ratethis.productservice.repository.country;

import com.ratethis.productservice.model.country.CountryKeys;
import org.springframework.data.repository.CrudRepository;

public interface CountryKeysRepository extends CrudRepository<CountryKeys,Long> {

    void deleteAllByProductId(long productId);

}
