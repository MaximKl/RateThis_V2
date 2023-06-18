package com.ratethis.profileservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "user_profile")
@DynamicUpdate(true)
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "profile_id")
    private long id;

    @Column( name = "profile_nick")
    private String nick;

    @Column( name = "profile_password")
    private String password;

    @Column( name = "profile_email")
    private String email;

    @Column( name = "profile_desc")
    private String description;

    @Column( name = "profile_reg_date")
    private LocalDateTime regDate;

    @Column( name = "profile_birthday")
    private Date birthday;

    @Column( name = "profile_report")
    private long reportQuantity;

    @Column( name = "profile_image")
    private String avatar;

    @Column( name = "profile_role")
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    @Column( name = "profile_color")
    private long color;

    @Column( name = "approve_friends_count")
    private long approveFriendsCount;

}
