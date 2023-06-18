package com.ratethis.chatservice.service;

import com.ratethis.chatservice.dto.MessageDTO;
import com.ratethis.chatservice.dto.ReceivedMessageDTO;
import com.ratethis.chatservice.dto.mapper.MessageDTOMapper;
import com.ratethis.chatservice.dto.mapper.ReceivedMessageDTOMapper;
import com.ratethis.chatservice.exception.IdentificationException;
import com.ratethis.chatservice.exception.TypeErrorException;
import com.ratethis.chatservice.model.Message;
import com.ratethis.chatservice.repository.MessageRepository;
import com.ratethis.chatservice.repository.ValidationProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ratethis.chatservice.constants.Constants.isNumberCheck;
import static com.ratethis.chatservice.constants.Constants.userIdIdentification;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final MessageDTOMapper messageDTOMapper;

    private final ValidationProxy validationProxy;

    private final ReceivedMessageDTOMapper receivedMessageDTOMapper;

    public List<MessageDTO> getAllChatMessages(String friendId, String token) {
        if (!isNumberCheck(friendId))
            throw new TypeErrorException();

        if (validationProxy.getValidation(token).getStatusCode().value() == 201)
            throw new IdentificationException();

        return Stream.concat(
                        messageRepository.findAllByMessageToAndMessageFrom(Long.parseLong(friendId), userIdIdentification(token)).stream(),
                        messageRepository.findAllByMessageToAndMessageFrom(userIdIdentification(token), Long.parseLong(friendId)).stream())
                .sorted((m1, m2) -> (int) (m1.getMessageTime().getTime() - m2.getMessageTime().getTime())).map(messageDTOMapper).toList();
    }


    public MessageDTO saveMessage(ReceivedMessageDTO receivedMessage, String token) {
        if (validationProxy.getValidation(token).getStatusCode().value() == 201)
            throw new IdentificationException();

        return messageDTOMapper.apply(
                messageRepository.save(
                        receivedMessageDTOMapper.apply(receivedMessage, userIdIdentification(token))));
    }

    public Set<MessageDTO> getAllLastMessages(String token) {
        if (validationProxy.getValidation(token).getStatusCode().value() == 201)
            throw new IdentificationException();

        List<Message> messages = Stream.concat(
                messageRepository.findAllByMessageFrom(userIdIdentification(token)).stream(),
                messageRepository.findAllByMessageTo(userIdIdentification(token)).stream())
                .sorted((m1, m2) -> (int) (m2.getMessageTime().getTime() - m1.getMessageTime().getTime())).toList();

        return new HashSet<>(messages).stream().map(messageDTOMapper).collect(Collectors.toSet());


    }
}
