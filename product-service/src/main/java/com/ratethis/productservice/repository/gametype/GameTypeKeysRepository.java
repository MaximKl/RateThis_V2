package com.ratethis.productservice.repository.gametype;

import com.ratethis.productservice.model.gametype.GameTypeKeys;
import org.springframework.data.repository.CrudRepository;

public interface GameTypeKeysRepository extends CrudRepository<GameTypeKeys,Long> {

    void deleteAllByProductId(long productId);

}
