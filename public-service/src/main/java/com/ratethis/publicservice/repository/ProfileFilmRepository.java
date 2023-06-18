package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.ProfileBook;
import com.ratethis.publicservice.model.ProfileFilm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileFilmRepository extends CrudRepository<ProfileFilm,Long> {

    Optional<ProfileFilm> findByProfileEntityNickAndProductId(String nick, long productId);
    List<ProfileFilm> findAllByProfileEntityNickAndProductEntityTypeId(String nick, long productTypeId);
}
