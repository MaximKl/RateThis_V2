package com.ratethis.productservice.repository.genre;

import com.ratethis.productservice.model.genre.GenreKeys;
import org.springframework.data.repository.CrudRepository;

public interface GenreKeysRepository extends CrudRepository<GenreKeys,Long> {
    void deleteAllByProductId(long productId);
}
