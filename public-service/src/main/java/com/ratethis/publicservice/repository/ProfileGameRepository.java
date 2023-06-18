package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.ProfileBook;
import com.ratethis.publicservice.model.ProfileGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileGameRepository extends CrudRepository<ProfileGame,Long> {

    Optional<ProfileGame> findByProfileEntityNickAndProductId(String nick, long productId);
    List<ProfileGame> findAllByProfileEntityNickAndProductEntityTypeId(String nick, long productTypeId);
}
