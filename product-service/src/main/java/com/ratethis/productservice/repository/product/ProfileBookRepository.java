package com.ratethis.productservice.repository.product;

import com.ratethis.productservice.model.product.profile.ProfileBook;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileBookRepository extends CrudRepository<ProfileBook, Long> {

    Optional<ProfileBook> findProfileBookByProfileIdAndProductId(long profileId, long productId);

    void deleteProfileBookByProfileIdAndProductId(long profileId, long productId);

}
