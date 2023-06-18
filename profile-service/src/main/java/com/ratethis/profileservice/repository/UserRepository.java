package com.ratethis.profileservice.repository;

import com.ratethis.profileservice.model.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <UserProfile, Long> {


    List<UserProfile> findAllBy();
    Optional<UserProfile> findByNickOrEmail(String nick, String email);

    @Query(value = "call is_unique_nick(:nick)", nativeQuery = true)
    byte checkNick(@Param("nick") String nick);

    @Query(value = "call is_unique_mail(:mail)", nativeQuery = true)
    byte checkMail(@Param("mail") String mail);

}
