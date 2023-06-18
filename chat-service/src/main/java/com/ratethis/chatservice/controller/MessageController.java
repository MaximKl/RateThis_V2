package com.ratethis.chatservice.controller;

import com.ratethis.chatservice.dto.MessageDTO;
import com.ratethis.chatservice.dto.ReceivedMessageDTO;
import com.ratethis.chatservice.service.MessageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.ratethis.chatservice.constants.Constants.userIdIdentification;

@RestController
@RequestMapping(value = "/RateThis-chat")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/allMessages/{friendId}")
    public ResponseEntity<List<MessageDTO>> getAllMessages(@PathVariable(value = "friendId") String friendId, @CookieValue(value = "token") String token) {
        return new ResponseEntity<>(messageService.getAllChatMessages(friendId, token), HttpStatus.OK);
    }

    @MessageMapping("/send-message")
    public void recMessage(@Payload @Valid ReceivedMessageDTO message, @Header(value = "token") String token) {
        MessageDTO savedMessage = messageService.saveMessage(message, token);
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getToUserId()), "/private", savedMessage);
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(userIdIdentification(token)), "/private", savedMessage);
    }

    @GetMapping("/recentMessages")
    public ResponseEntity<Set<MessageDTO>> getAllMessages(@CookieValue(value = "token") String token) {
        return new ResponseEntity<>(messageService.getAllLastMessages(token), HttpStatus.OK);
    }

}
