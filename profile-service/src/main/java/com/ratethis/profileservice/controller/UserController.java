package com.ratethis.profileservice.controller;

import com.ratethis.profileservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("RateThis")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/addFriend/{friendId}")
    public ResponseEntity addFriend(@PathVariable("friendId")String friendId, @CookieValue(value = "token") String token) {
        userService.saveSendFriendMessage(token, friendId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/removeFriend/{friendId}")
    public ResponseEntity removeFriend(@PathVariable("friendId")String friendId, @CookieValue(value = "token") String token) {
        userService.deleteFriend(token, friendId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
