package com.ratethis.reviewservice.constants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratethis.reviewservice.exception.IdentificationException;
import com.ratethis.reviewservice.model.UserRole;

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



}
