package com.ratethis.publicservice.repository;

import com.ratethis.publicservice.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserProfile,Long> {

    Optional<UserProfile> findByNick(String nick);

}
