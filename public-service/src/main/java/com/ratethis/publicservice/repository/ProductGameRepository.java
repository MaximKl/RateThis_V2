package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.ProductGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductGameRepository extends CrudRepository<ProductGame, Long> {
}
