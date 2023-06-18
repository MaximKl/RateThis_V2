package com.ratethis.profileservice.dto;

import com.ratethis.profileservice.model.UserRole;

public record UserToSend(
        long id,
        String username,
        String avatar,
        UserRole role,
        long friendsApproveCount) {
}
