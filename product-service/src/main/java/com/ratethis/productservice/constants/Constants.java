package com.ratethis.productservice.constants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratethis.productservice.exception.IdentificationException;
import com.ratethis.productservice.exception.TypeErrorException;
import com.ratethis.productservice.model.UserRole;

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

    static String userNameIdentification(String token) {
        return (String) getUserInfoMap(token).get("sub");
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

    static String saveProductPicture(String picture, long id) {
        byte[] decodedImage = Base64.getDecoder().decode(picture.split(",")[1].getBytes(StandardCharsets.UTF_8));
        if (decodedImage.length > 3_145_728)
            throw new TypeErrorException();
        String pictureName = MessageFormat.format("picture_of_product_{0}.jpg", id);
        Path saveTo = Path.of("product-service/src/main/resources/static/product_pictures", pictureName);
        try {
            Files.write(saveTo, decodedImage);
        } catch (IOException e) {
            throw new TypeErrorException();
        }
        return pictureName;
    }

    static String developerPictureParser(String fileName) {
        String image = "";
        if (fileName.startsWith("link:")) {
            image = fileName;
        } else {
            Path takeFrom = Path.of("product-service/src/main/resources/static/developer_photos", fileName);
            try {
                byte[] imageBytes = Files.readAllBytes(takeFrom);
                image = Base64.getEncoder().encodeToString(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }

    static String saveDeveloperPicture(String picture, int id) {
        byte[] decodedImage = Base64.getDecoder().decode(picture.split(",")[1].getBytes(StandardCharsets.UTF_8));
        if (decodedImage.length > 3_145_728)
            throw new TypeErrorException();
        String pictureName = MessageFormat.format("photo_of_developer_{0}.jpg", id);
        Path saveTo = Path.of("product-service/src/main/resources/static/developer_photos", pictureName);
        try {
            Files.write(saveTo, decodedImage);
        } catch (IOException e) {
            throw new TypeErrorException();
        }
        return pictureName;
    }

}
