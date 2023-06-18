package com.ratethis.profileservice.controller;

import com.ratethis.profileservice.authentication.Authenticate;
import com.ratethis.profileservice.dto.*;
import com.ratethis.profileservice.model.UserRole;
import com.ratethis.profileservice.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("RateThis")
@AllArgsConstructor
public class AuthorizationController {

    private final AuthorizationService service;

    @PostMapping("/registration")
    public ResponseEntity<UserCreateResponse> regUser(@RequestBody UserRegistrationInfo user) {
        service.createUser(user);
        return new ResponseEntity<>(new UserCreateResponse("OK"), HttpStatus.CREATED);
    }

    @GetMapping("/findUserByNick/{nick}")
    public UserLoginInfo logUser(@PathVariable("nick") String nickOrMail) {
        return service.authorizeUser(nickOrMail);
    }

    @GetMapping("/findUserToSend/{nick}")
    public UserToSend sendUserInfo(@PathVariable("nick") String nickOrMail) {
        return service.sendUserInfo(nickOrMail);
    }

    @GetMapping("/findUserById/{id}")
    public UserToSend sendUserInfo(@PathVariable("id") long id) {
        return service.findUserById(id);
    }

    @PostMapping("/settings")
    public ResponseEntity<UserToSend> updateUserProfile(@RequestBody UserRegistrationInfo user, @CookieValue(value = "token") String token) {
        return new ResponseEntity<>(service.updateUserInfo(user, token), HttpStatus.CREATED);
    }

    @GetMapping("/settings")
    public ResponseEntity<UserSettingsDTO> getUserProfile(@CookieValue(value = "token") String token) {
        return new ResponseEntity<>(service.getUserInfoForSettings(token), HttpStatus.OK);
    }

    @Authenticate(role = UserRole.ADMIN)
    @GetMapping("/isAdmin")
    public ResponseEntity isAdmin(@CookieValue(value = "token") String token) {
        return new ResponseEntity (HttpStatus.OK);
    }

    @Authenticate(role = UserRole.ADMIN)
    @DeleteMapping("/deleteProfileByAdmin/{profileId}")
    public ResponseEntity removeProfileByAdmin(@PathVariable("profileId")String profileId, @CookieValue(value = "token") String token) {
        service.deleteProfileByAdmin(profileId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteProfile/{profileId}")
    public ResponseEntity removeProfile(@PathVariable("profileId")String profileId, @CookieValue(value = "token") String token) {
        service.deleteProfile(token,profileId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
