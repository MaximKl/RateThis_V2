package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {

    List<Product> findByTypeId(long id);

    List<Product> findByTypeIdOrderByEstimationQuantityDesc(long id, Pageable quantity);

}
