package com.ratethis.publicservice.constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public interface Constants {

    static boolean isNumberCheck(String s) {
        try {
            Long.parseLong(s);
        } catch (NumberFormatException nf) {
            return false;
        }
        return true;
    }

    static String productPictureParser(String fileName) {
        String image = "";
        if (fileName.startsWith("link:")) {
            image = fileName;
        } else {
            Path takeFrom = Path.of("product-service/src/main/resources/static/product_pictures", fileName);
            try {
                byte[] imageBytes = Files.readAllBytes(takeFrom);
                image = Base64.getEncoder().encodeToString(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
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

}
