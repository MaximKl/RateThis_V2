package com.ratethis.productservice.repository.product;

import com.ratethis.productservice.model.product.profile.ProfileFilm;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileFilmRepository extends CrudRepository<ProfileFilm, Long> {

    Optional<ProfileFilm> findProfileFilmByProfileIdAndProductId(long profileId, long productId);

    void deleteProfileFilmByProfileIdAndProductId(long profileId, long productId);
}
