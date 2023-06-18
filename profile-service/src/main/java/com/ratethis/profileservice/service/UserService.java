package com.ratethis.profileservice.service;

import com.ratethis.profileservice.constants.Constants;
import com.ratethis.profileservice.exception.TypeErrorException;
import com.ratethis.profileservice.model.Friend;
import com.ratethis.profileservice.repository.FriendRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final FriendRepository friendRepository;

    public void saveSendFriendMessage(String token, String id) {
        if (!Constants.isNumberCheck(id))
            throw new TypeErrorException();
        long senderId = Constants.userIdIdentification(token);
        long receiverId = Long.parseLong(id);
        if (senderId == receiverId)
            throw new TypeErrorException();

        Optional<Friend> friendOne = friendRepository.findFriendByFriendOneAndFriendTwo(receiverId, senderId);
        Optional<Friend> friendTwo = friendRepository.findFriendByFriendOneAndFriendTwo(senderId, receiverId);
        if (friendTwo.isPresent())
            return;
        if (friendOne.isPresent()) {
            friendOne.get().setFriendIsApprove(true);
            friendRepository.save(friendOne.get());
            return;
        }
        friendRepository.save(new Friend(0, senderId, receiverId, false));
    }

    @Transactional
    public void deleteFriend(String token, String friendId) {
        if (!Constants.isNumberCheck(friendId))
            throw new TypeErrorException();
        friendRepository.deleteFriendByFriendOneAndFriendTwo(Long.parseLong(friendId), Constants.userIdIdentification(token));
        friendRepository.deleteFriendByFriendOneAndFriendTwo(Constants.userIdIdentification(token), Long.parseLong(friendId));
    }

}
