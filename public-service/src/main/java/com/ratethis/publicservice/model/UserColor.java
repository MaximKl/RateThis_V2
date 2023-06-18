package com.ratethis.publicservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_color")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserColor {

    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "color_name")
    private String name;

}
