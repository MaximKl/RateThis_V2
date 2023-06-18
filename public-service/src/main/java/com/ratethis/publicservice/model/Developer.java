package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "developer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Developer {

    @Id
    @Column(name = "developer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "developer_name")
    private String name;

    @Column(name = "developer_photo")
    private String photo;

    @Column(name = "developer_description")
    private String description;

    @Column(name = "developer_birthday")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "developer_country")
    private Country country;

    @ManyToMany
    @JoinTable(name = "product_developer", inverseJoinColumns = {@JoinColumn(name = "product_id")}, joinColumns = {@JoinColumn(name = "developer_id")})
    @MapKeyJoinColumn(name = "role_id")
    private Map<DeveloperRole,Product> projects;

}
