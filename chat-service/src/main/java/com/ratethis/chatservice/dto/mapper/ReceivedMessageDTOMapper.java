package com.ratethis.chatservice.dto.mapper;

import com.ratethis.chatservice.dto.ReceivedMessageDTO;
import com.ratethis.chatservice.model.Message;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.function.Function;

@Service
public class ReceivedMessageDTOMapper {

    public Message apply(ReceivedMessageDTO receiveMessageDTO, long senderId) {
        return new Message(
                0,
                receiveMessageDTO.getBody(),
                Timestamp.valueOf(LocalDateTime.now()),
                senderId,
                receiveMessageDTO.getToUserId(),
                false
        );
    }
}
