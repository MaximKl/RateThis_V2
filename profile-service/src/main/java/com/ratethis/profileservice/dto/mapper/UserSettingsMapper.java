package com.ratethis.profileservice.dto.mapper;

import com.ratethis.profileservice.dto.UserSettingsDTO;
import com.ratethis.profileservice.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import static com.ratethis.profileservice.constants.Constants.userAvatarParser;

@Service
public class UserSettingsMapper implements Function<UserProfile, UserSettingsDTO> {


    @Override
    public UserSettingsDTO apply(UserProfile profile) {
        return new UserSettingsDTO(
                profile.getNick(),
                profile.getEmail(),
                profile.getDescription(),
                profile.getBirthday(),
                userAvatarParser(profile.getAvatar()));

    }
}
