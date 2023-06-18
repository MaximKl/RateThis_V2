package com.ratethis.publicservice.dto.mapper.usermap;

import com.ratethis.publicservice.dto.userdto.FriendDTO;
import com.ratethis.publicservice.dto.userdto.LightFriendDTO;
import com.ratethis.publicservice.dto.userdto.ProfileDTO;
import com.ratethis.publicservice.model.UserFriend;
import com.ratethis.publicservice.model.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ratethis.publicservice.constants.Constants.userAvatarParser;

@Service
@AllArgsConstructor
public class ProfileDTOMapper implements Function<UserProfile, ProfileDTO> {


    private final UserFriendDTOMapper friendMapper;


    @Override
    public ProfileDTO apply(UserProfile userProfile) {

        Set<UserFriend> allFriends = Stream.concat(
                userProfile.getFriendsOne().stream().filter(UserFriend::isFriendIsApprove),
                userProfile.getFriendsTwo().stream().filter(UserFriend::isFriendIsApprove)).collect(Collectors.toSet());


        List<LightFriendDTO> firstUserFriend = allFriends.stream().map(friendMapper::applyFriendOneLightMapper).toList();
        List<LightFriendDTO> secondUserFriend = allFriends.stream().map(friendMapper::applyFriendTwoLightMapper).toList();

        return new ProfileDTO(
                userProfile.getId(),
                userProfile.getNick(),
                userProfile.getDesc(),
                userProfile.getRegDate(),
                userProfile.getBirthday(),
                userProfile.getReport(),
                userAvatarParser(userProfile.getImage()),
                userProfile.getUserRole().getName(),
                userProfile.getUserColor().getName(),
                Stream.concat(firstUserFriend.stream(),secondUserFriend.stream()).filter(f->!f.name().equals(userProfile.getNick())).count()
        );
    }
}
