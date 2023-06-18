package com.ratethis.profileservice.dto.mapper;

import com.ratethis.profileservice.dto.UserLoginInfo;
import com.ratethis.profileservice.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class LoginInfoMapper implements Function<UserProfile, UserLoginInfo> {

    @Override
    public UserLoginInfo apply(UserProfile userProfile) {
        return new UserLoginInfo(
                userProfile.getId(),
                userProfile.getNick(),
                userProfile.getPassword(),
                userProfile.getRole()
        );
    }
}
