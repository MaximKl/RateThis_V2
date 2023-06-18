package com.ratethis.chatservice.dto.mapper;

import com.ratethis.chatservice.dto.MessageDTO;
import com.ratethis.chatservice.model.Message;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MessageDTOMapper implements Function<Message, MessageDTO> {

    @Override
    public MessageDTO apply(Message message) {
        return new MessageDTO(
                message.getMessageId(),
                message.getMessageBody(),
                message.getMessageTime(),
                message.getMessageFrom(),
                message.getMessageTo(),
                message.isMessageIsRead());
    }

}
