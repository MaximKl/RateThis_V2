package com.ratethis.profileservice.dto.mapper;

import com.ratethis.profileservice.dto.UserRegistrationInfo;
import com.ratethis.profileservice.model.UserProfile;
import com.ratethis.profileservice.model.UserRole;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Function;

import static com.ratethis.profileservice.constants.Constants.saveUserAvatar;

@Service
public class RegistrationInfoMapper implements Function<UserRegistrationInfo, UserProfile> {

    @Override
    public UserProfile apply(UserRegistrationInfo regInfo) {

        return new UserProfile(
                0,
                regInfo.nick(),
                regInfo.password(),
                regInfo.email(),
                regInfo.description(),
                LocalDateTime.now(),
                regInfo.birthday(),
                0,
                saveUserAvatar(regInfo.avatar(), regInfo.nick()),
                UserRole.DEFAULT,
                1,
                0);
    }

}
