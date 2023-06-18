package com.ratethis.productservice.repository.gametype;


import com.ratethis.productservice.model.gametype.GameType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameTypeRepository extends CrudRepository<GameType, Long> {
    List<GameType> findAllBy();
}
