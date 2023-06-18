package com.ratethis.apigateway.user;

public record UserToSendDTO(
        long id,
        String username,
        String avatar,
        String token,
        UserRole role,
        long friendsApproveCount) {
}

