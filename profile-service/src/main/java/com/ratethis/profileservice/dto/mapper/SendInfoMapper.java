package com.ratethis.profileservice.dto.mapper;

import com.ratethis.profileservice.dto.UserToSend;
import com.ratethis.profileservice.model.UserProfile;
import org.springframework.stereotype.Service;

import static com.ratethis.profileservice.constants.Constants.userAvatarParser;

import java.util.function.Function;

@Service
public class SendInfoMapper implements Function<UserProfile, UserToSend> {

    @Override
    public UserToSend apply(UserProfile userProfile) {

        return new UserToSend(
                userProfile.getId(),
                userProfile.getNick(),
                userAvatarParser(userProfile.getAvatar()),
                userProfile.getRole(),
                userProfile.getApproveFriendsCount());
    }
}
