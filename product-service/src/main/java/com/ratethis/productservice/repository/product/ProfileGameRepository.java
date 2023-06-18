package com.ratethis.productservice.repository.product;

import com.ratethis.productservice.model.product.profile.ProfileGame;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileGameRepository extends CrudRepository<ProfileGame, Long> {

    Optional<ProfileGame> findProfileGameByProfileIdAndProductId(long profileId, long productId);

    void deleteProfileGameByProfileIdAndProductId(long profileId, long productId);

}
