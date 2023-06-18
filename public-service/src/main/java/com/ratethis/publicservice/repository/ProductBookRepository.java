package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.ProductBook;
import org.springframework.data.repository.CrudRepository;

public interface ProductBookRepository extends CrudRepository<ProductBook, Long> {
}
