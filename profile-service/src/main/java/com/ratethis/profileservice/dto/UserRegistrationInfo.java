package com.ratethis.profileservice.dto;


import java.util.Date;

public record UserRegistrationInfo(String email,

                                   Date birthday,

                                   String description,

                                   String nick,

                                   String password,

                                   String avatar) {


}
