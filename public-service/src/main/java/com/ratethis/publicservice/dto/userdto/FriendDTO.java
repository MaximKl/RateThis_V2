package com.ratethis.publicservice.dto.userdto;

import java.sql.Date;
import java.sql.Timestamp;

public record FriendDTO(
        String name,
        String avatar,
        String color,
        String role,
        Date birthday,
        Timestamp regDate,
        long friendId,
        boolean isApproved
        ) {
}
