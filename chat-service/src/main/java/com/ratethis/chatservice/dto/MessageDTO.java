package com.ratethis.chatservice.dto;

import java.sql.Timestamp;

public record MessageDTO(

        long messageId,

       String messageBody,

       Timestamp messageTime,

       long messageFrom,
       long messageTo,

       boolean messageIsRead

) {
}
