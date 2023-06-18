package com.ratethis.publicservice.dto.mapper.usermap;

import com.ratethis.publicservice.dto.userdto.FriendDTO;
import com.ratethis.publicservice.dto.userdto.LightFriendDTO;
import com.ratethis.publicservice.model.UserFriend;
import com.ratethis.publicservice.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ratethis.publicservice.constants.Constants.userAvatarParser;


@Service
public class UserFriendDTOMapper {
    public FriendDTO applyFriendOne(UserFriend userFriend) {
        return new FriendDTO(
                userFriend.getFriendTwo().getNick(),
                userAvatarParser(userFriend.getFriendTwo().getImage()),
                userFriend.getFriendTwo().getUserColor().getName(),
                userFriend.getFriendTwo().getUserRole().getName(),
                userFriend.getFriendTwo().getBirthday(),
                userFriend.getFriendTwo().getRegDate(),
                userFriend.getFriendTwo().getId(),
                userFriend.isFriendIsApprove()
        );
    }

    public FriendDTO applyFriendTwo(UserFriend userFriend) {
        return new FriendDTO(
                userFriend.getFriendOne().getNick(),
                userAvatarParser(userFriend.getFriendOne().getImage()),
                userFriend.getFriendOne().getUserColor().getName(),
                userFriend.getFriendOne().getUserRole().getName(),
                userFriend.getFriendOne().getBirthday(),
                userFriend.getFriendOne().getRegDate(),
                userFriend.getFriendOne().getId(),
                userFriend.isFriendIsApprove()
        );
    }


    public LightFriendDTO applyFriendTwoLightMapper(UserFriend userFriend) {
        return new LightFriendDTO(userFriend.getFriendOne().getNick(), userFriend.isFriendIsApprove());
    }

    public LightFriendDTO applyFriendOneLightMapper(UserFriend userFriend) {
        return new LightFriendDTO(userFriend.getFriendTwo().getNick(), userFriend.isFriendIsApprove());

    }

    public List<FriendDTO> applyAllFriends(UserProfile userProfile) {
        Set<UserFriend> allFriends = Stream.concat(
                userProfile.getFriendsOne().stream().filter(UserFriend::isFriendIsApprove),
                userProfile.getFriendsTwo().stream().filter(UserFriend::isFriendIsApprove)).collect(Collectors.toSet());

        List<FriendDTO> firstUserFriend = allFriends.stream().map(this::applyFriendOne).toList();
        List<FriendDTO> secondUserFriend = allFriends.stream().map(this::applyFriendTwo).toList();

        return Stream.concat(firstUserFriend.stream(), secondUserFriend.stream()).filter(f -> !f.name().equals(userProfile.getNick())).toList();
    }

    public List<FriendDTO> applyFriendSuggestion(UserProfile userProfile) {
        Set<FriendDTO> suggestions = userProfile.getFriendsTwo().stream().filter(f->!f.isFriendIsApprove()).map(this::applyFriendTwo).collect(Collectors.toSet());
        return suggestions.stream().toList();
    }


    public List<LightFriendDTO> applyLightFriends(UserProfile userProfile) {
        Set<UserFriend> allFriends = Stream.concat(
                userProfile.getFriendsOne().stream().filter(UserFriend::isFriendIsApprove),
                userProfile.getFriendsTwo().stream()).collect(Collectors.toSet());

        List<LightFriendDTO> firstUserFriend = allFriends.stream().map(this::applyFriendOneLightMapper).toList();
        List<LightFriendDTO> secondUserFriend = allFriends.stream().map(this::applyFriendTwoLightMapper).toList();

        return Stream.concat(firstUserFriend.stream(), secondUserFriend.stream()).filter(f -> !f.name().equals(userProfile.getNick())).toList();
    }
}
