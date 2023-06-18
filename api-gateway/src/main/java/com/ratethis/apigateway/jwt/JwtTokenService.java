package com.ratethis.apigateway.jwt;

import com.ratethis.apigateway.user.UserLoginInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;

    @Value("${jwt.token.expiration.hours}")
    private int expirationHours;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public Mono<String> generateToken(Mono<Authentication> authentication, String device) {

        Mono<JwtClaimsSet> scs = authentication.map(auth -> {
            UserLoginInfo user = (UserLoginInfo) auth.getPrincipal();
            return JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plus(expirationHours, ChronoUnit.HOURS))
                    .subject(auth.getName())
                    .claim("userID", user.getId())
                    .claim("device", device)
                    .claim("role", user.getRole())
                    .build();
        });

        return scs.map(d -> this.jwtEncoder
                .encode(JwtEncoderParameters.from(d))
                .getTokenValue());

    }
}

record JwtTokenResponse(String token) {
}