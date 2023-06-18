package com.ratethis.apigateway.jwt;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JwtTokenRequest {
    private String username;
    private String password;

    private String device;
}
