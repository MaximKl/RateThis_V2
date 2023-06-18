package com.ratethis.profileservice.repository;

import com.ratethis.profileservice.model.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FriendRepository extends CrudRepository<Friend, Long> {

    void deleteFriendByFriendOneAndFriendTwo(long f1, long f2);

    Optional<Friend> findFriendByFriendOneAndFriendTwo(long f1, long f2);

}
