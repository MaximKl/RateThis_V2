package com.ratethis.chatservice.constants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratethis.chatservice.exception.IdentificationException;

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

    static long userIdIdentification(String token) {
        String tokenObject = new String(Base64.getDecoder().decode(token.split("\\.")[1]));
        Map<String, Object> map;
        try {
            map = new ObjectMapper().readValue(tokenObject, Map.class);
        } catch (JsonProcessingException e) {
            throw new IdentificationException();
        }
        return (int) map.get("userID");
    }

}
