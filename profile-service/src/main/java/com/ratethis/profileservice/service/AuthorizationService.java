package com.ratethis.profileservice.service;

import com.ratethis.profileservice.dto.UserLoginInfo;
import com.ratethis.profileservice.dto.UserRegistrationInfo;
import com.ratethis.profileservice.dto.UserSettingsDTO;
import com.ratethis.profileservice.dto.UserToSend;
import com.ratethis.profileservice.dto.mapper.LoginInfoMapper;
import com.ratethis.profileservice.dto.mapper.RegistrationInfoMapper;
import com.ratethis.profileservice.dto.mapper.SendInfoMapper;
import com.ratethis.profileservice.dto.mapper.UserSettingsMapper;
import com.ratethis.profileservice.exception.*;
import com.ratethis.profileservice.model.UserProfile;
import com.ratethis.profileservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static com.ratethis.profileservice.constants.Constants.*;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final UserRepository repository;

    private final RegistrationInfoMapper regInfoMapper;
    private final LoginInfoMapper loginInfoMapper;

    private final SendInfoMapper sendInfoMapper;

    private final UserSettingsMapper userSettingsMapper;

    private boolean nickValidation(String nick) {
        return Character.isLowerCase(nick.charAt(0))
                || Pattern.matches(".*[А-Яа-яєЄїЇіІґҐ].*", nick)
                || !Pattern.matches("^[A-Za-z0-9_]+$", nick)
                || nick.length() < 3;
    }

    private boolean birthdayValidation(Date birthday) {
        LocalDateTime userBirth = Instant.ofEpochMilli(birthday.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return LocalDateTime.now().minusYears(3).isBefore(userBirth) ||
                LocalDateTime.now().minusYears(100).isAfter(userBirth);
    }

    public UserLoginInfo createUser(UserRegistrationInfo regInfo) {

        if (repository.checkMail(regInfo.email()) == 0)
            throw new ExistEmailException();

        if (repository.checkNick(regInfo.nick()) == 0)
            throw new ExistNickException();

        if (nickValidation(regInfo.nick()))
            throw new ValidationException();

        if (!regInfo.email().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"))
            throw new ValidationException();

        if (birthdayValidation(regInfo.birthday()))
            throw new ValidationException();

        if (regInfo.description().length() > 2000)
            throw new ValidationException();

        return loginInfoMapper.apply(repository.save(regInfoMapper.apply(regInfo)));
    }

    public UserLoginInfo authorizeUser(String userInfo) {
        return loginInfoMapper.apply(repository.findByNickOrEmail(userInfo, userInfo).orElseThrow(WrongEmailOrNickException::new));
    }

    public UserToSend sendUserInfo(String nick) {
        return sendInfoMapper.apply(repository.findByNickOrEmail(nick, nick).orElseThrow(ValidationException::new));
    }

    public UserToSend findUserById(long id) {
        return sendInfoMapper.apply(repository.findById(id).orElseThrow(ValidationException::new));
    }


    public UserSettingsDTO getUserInfoForSettings(String token) {
        return repository.findById(userIdIdentification(token)).map(userSettingsMapper).get();
    }

    public UserToSend updateUserInfo(UserRegistrationInfo user, String token) {
        UserProfile oldUser = repository.findById(userIdIdentification(token)).get();
        boolean somethingHadChanged = false;

        if (!user.email().equalsIgnoreCase(oldUser.getEmail())) {
            if (repository.checkMail(user.email()) == 0)
                throw new ExistEmailException();
            if (!user.email().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"))
                throw new ValidationException();
            somethingHadChanged = true;
            oldUser.setEmail(user.email());
        }

        if (!user.nick().equalsIgnoreCase(oldUser.getNick())) {
            if (repository.checkNick(user.nick()) == 0)
                throw new ExistNickException();
            if (nickValidation(user.nick()))
                throw new ValidationException();
            somethingHadChanged = true;
            oldUser.setNick(user.nick());
        }

        if (!(user.avatar().equals("DEF_IMAGE") && oldUser.getAvatar().equals("default_avatar.jpg"))) {
            String avatar = userAvatarParser(oldUser.getAvatar());
            if (!user.avatar().equals(avatar)) {
                somethingHadChanged = true;
                oldUser.setAvatar(saveUserAvatar(user.avatar(), user.nick()));
            }
        }

        if (!user.description().equals(oldUser.getDescription())) {
            if (user.description().length() > 2000)
                throw new ValidationException();
            somethingHadChanged = true;
            oldUser.setDescription(user.description());
        }

        if (user.birthday().compareTo(oldUser.getBirthday()) != 0) {
            if (birthdayValidation(user.birthday()))
                throw new ValidationException();
            somethingHadChanged = true;
            oldUser.setBirthday(user.birthday());
        }

        if (somethingHadChanged) {
            return sendInfoMapper.apply(repository.save(oldUser));
        }

        return sendInfoMapper.apply(oldUser);
    }

    @Transactional
    public void deleteProfileByAdmin(String profileId) {
        if(!isNumberCheck(profileId))
            throw new TypeErrorException();
        repository.deleteById(Long.parseLong(profileId));
    }

    @Transactional
    public void deleteProfile(String token, String profileId) {
        if(!isNumberCheck(profileId))
            throw new TypeErrorException();

        if(userIdIdentification(token)!=Long.parseLong(profileId))
            throw new ValidationException();

        repository.deleteById(Long.parseLong(profileId));
    }

}
