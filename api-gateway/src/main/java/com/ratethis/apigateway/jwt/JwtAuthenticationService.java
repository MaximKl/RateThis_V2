package com.ratethis.apigateway.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.ratethis.apigateway.exceptions.ValidationException;
import com.ratethis.apigateway.user.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService {

    private final JwtTokenService tokenService;

    private final ReactiveAuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final UserLoginProxy proxy;

    private final JWTVerifier verify;

    @Value("${jwt.token.expiration.hours}")
    private int expirationHours;

    private final HttpCookie REMOVE_TOKEN = ResponseCookie.from("token", "0")
            .path("/")
            .maxAge(0)
            .secure(true)
            .httpOnly(true)
            .sameSite("Strict")
            .build();


    public Mono<ResponseEntity<UserToSendDTO>> getGeneratedToken(JwtTokenRequest request) {
        return tokenService
                .generateToken(authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())), request.getDevice())
                .map(d -> {
                    if (!d.isBlank()) {
                        return Mono.fromCallable(() -> proxy.getUserToSend(request.getUsername())).subscribeOn(Schedulers.boundedElastic())
                                .map(user -> {
                                    HttpCookie tokenCookie = ResponseCookie.from("token", d)
                                            .path("/")
                                            .maxAge(Duration.ofHours(expirationHours))
                                            .secure(true)
                                            .httpOnly(true)
                                            .sameSite("Strict")
                                            .build();
                                    return ResponseEntity.ok()
                                            .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                                            .body(new UserToSendDTO(user.getId(), user.getUsername(), user.getAvatar(), d, user.getRole(),user.getFriendsApproveCount()));
                                });
                    }
                    return null;
                }).flatMap(mono -> mono);
    }

    public Mono<UserCreateResponse> createUser(UserRegistrationInfo regInfo) {
        if (regInfo.getPassword().length() < 6 || !Pattern.matches("^[A-Za-z0-9]+$", regInfo.getPassword()))
            throw new ValidationException();
        regInfo.setPassword("{bcrypt}" + encoder.encode(regInfo.getPassword()));
        return Mono.fromCallable(() -> proxy.regUser(regInfo)).subscribeOn(Schedulers.boundedElastic());
    }

    public ResponseEntity validateUser(String stringToken, String device) {
        if (stringToken == null && device != null) {
            return new ResponseEntity(new UserCreateResponse("unvalid token"), HttpStatusCode.valueOf(401));
        }

        DecodedJWT token;
        try {
            token = verify.verify(stringToken);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header(HttpHeaders.SET_COOKIE, REMOVE_TOKEN.toString()).body(new UserCreateResponse("unvalid token"));
        }

        if (!token.getClaim("device").asString().equals(device)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header(HttpHeaders.SET_COOKIE, REMOVE_TOKEN.toString()).body(new UserCreateResponse("unvalid token"));
        }

        return new ResponseEntity(Mono.fromCallable(() -> proxy.getUserById(token.getClaim("userID").asLong()))
                .subscribeOn(Schedulers.boundedElastic())
                .map(user -> new UserToSendDTO(user.getId(), user.getUsername(), user.getAvatar(), token.getToken(), user.getRole(), user.getFriendsApproveCount())), HttpStatusCode.valueOf(200));
    }

    public ResponseEntity validateChatMessage(String receivedToken) {
        if (receivedToken == null) {
            return new ResponseEntity(new UserCreateResponse("unvalid token"), HttpStatusCode.valueOf(201));
        }
        try {
            verify.verify(receivedToken);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(new UserCreateResponse("unvalid token"));
        }
        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }


    public ResponseEntity invalidateToken() {
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, REMOVE_TOKEN.toString()).build();

    }
}
