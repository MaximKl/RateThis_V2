package com.ratethis.profileservice.dto;


import com.ratethis.profileservice.model.UserRole;


public record UserLoginInfo(
        long id,
        String username,
        String password,
        UserRole role) {
}
