package com.ratethis.apigateway.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserToSend {
    private long id;
    private String username;
    private String avatar;
    private UserRole role;
    private long friendsApproveCount;
}
