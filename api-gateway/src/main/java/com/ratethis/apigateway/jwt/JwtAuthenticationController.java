package com.ratethis.apigateway.jwt;

import com.ratethis.apigateway.user.UserCreateResponse;
import com.ratethis.apigateway.user.UserRegistrationInfo;
import com.ratethis.apigateway.user.UserToSendDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class JwtAuthenticationController {

    private final JwtAuthenticationService service;

    @PostMapping("/authorization")
    public Mono<ResponseEntity<UserToSendDTO>> authenticateUser(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return service.getGeneratedToken(jwtTokenRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity registerUser(@RequestBody UserRegistrationInfo regInfo) {
        return new ResponseEntity(service.createUser(regInfo), HttpStatus.CREATED);
    }

    @GetMapping("/validate")
    public ResponseEntity validateToken(@CookieValue(value = "token", required = false) String cookie, @RequestHeader HttpHeaders headers) {
        if (headers.get(HttpHeaders.USER_AGENT) == null)
            return new ResponseEntity(new UserCreateResponse("unvalid token"), HttpStatusCode.valueOf(401));
        return service.validateUser(cookie, headers.get(HttpHeaders.USER_AGENT).get(0));
    }

    @GetMapping("/validateChatMessage")
    public ResponseEntity validateChatToken(@RequestHeader HttpHeaders headers) {
        if (headers.get("token") == null)
            return new ResponseEntity(new UserCreateResponse("unvalid token"), HttpStatusCode.valueOf(201));
        return service.validateChatMessage(headers.get("token").get(0));
    }

    @GetMapping("/exit")
    public ResponseEntity exitFromProfile() {
        return service.invalidateToken();
    }
}