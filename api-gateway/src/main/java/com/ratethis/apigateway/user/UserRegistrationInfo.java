package com.ratethis.apigateway.user;


import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserRegistrationInfo {

    private String email;

    private Date birthday;

    private String description;

    private String nick;

    private String password;

    private String avatar;


}
