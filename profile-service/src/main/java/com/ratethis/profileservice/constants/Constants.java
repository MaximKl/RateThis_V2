package com.ratethis.profileservice.constants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratethis.profileservice.exception.IdentificationException;
import com.ratethis.profileservice.model.UserRole;
import jakarta.validation.ValidationException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.Map;

public interface Constants {

    static boolean isNumberCheck(String s) {
        try {
            Long.parseLong(s);
        } catch (NumberFormatException nf) {
            return false;
        }
        return true;
    }

    private static Map<String, Object> getUserInfoMap(String token) {
        String tokenObject = new String(Base64.getDecoder().decode(token.split("\\.")[1]));
        Map<String, Object> map;
        try {
            map = new ObjectMapper().readValue(tokenObject, Map.class);
        } catch (JsonProcessingException e) {
            throw new IdentificationException();
        }
        return map;
    }

    static long userIdIdentification(String token) {
        return (int) getUserInfoMap(token).get("userID");
    }

    static UserRole userRoleIdentification(String token) {
        return UserRole.valueOf((String) getUserInfoMap(token).get("role"));
    }

    static String userAvatarParser(String fileName) {
        String image = "";
        Path takeFrom = Path.of("profile-service/src/main/resources/static/avatars", fileName);
        try {
            byte[] imageBytes = Files.readAllBytes(takeFrom);
            image = Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    static String saveUserAvatar(String avatar, String nick) {
        String avatarName = "default_avatar.jpg";
        if (!avatar.equals("DEF_IMAGE")) {
            byte[] decodedImage = Base64.getDecoder().decode(avatar.split(",")[1].getBytes(StandardCharsets.UTF_8));
            if (decodedImage.length > 2_097_152)
                throw new ValidationException();
            avatarName = MessageFormat.format("avatar_of_user_{0}.jpg", nick);
            Path saveTo = Path.of("profile-service/src/main/resources/static/avatars", avatarName);
            try {
                Files.write(saveTo, decodedImage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return avatarName;
    }

}
