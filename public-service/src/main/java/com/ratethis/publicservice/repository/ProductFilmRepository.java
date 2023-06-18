package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.ProductBook;
import com.ratethis.publicservice.model.ProductFilm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductFilmRepository extends CrudRepository<ProductFilm, Long> {
}