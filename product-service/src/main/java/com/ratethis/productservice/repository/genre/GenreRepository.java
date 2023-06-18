package com.ratethis.productservice.repository.genre;

import com.ratethis.productservice.model.genre.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAllBy();

}
