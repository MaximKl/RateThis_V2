package com.ratethis.profileservice.dto;

import java.time.LocalDateTime;
import java.util.Date;


public record UserSettingsDTO(String nick,
                              String email,
                              String description,
                              Date birthday,
                              String avatar) {
}
