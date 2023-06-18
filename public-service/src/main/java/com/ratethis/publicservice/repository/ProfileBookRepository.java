package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.ProfileBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileBookRepository extends CrudRepository<ProfileBook, Long> {

    Optional<ProfileBook> findByProfileEntityNickAndProductId(String nick, long productId);
    List<ProfileBook> findAllByProfileEntityNickAndProductEntityTypeId(String nick, long productTypeId);
}
