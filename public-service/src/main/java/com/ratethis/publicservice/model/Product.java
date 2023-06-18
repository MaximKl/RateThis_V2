package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "product_name")
    private String name;
    @Basic
    @Column(name = "product_rating")
    private int rating;
    @Basic
    @Column(name = "product_add_date")
    private Timestamp addDate;
    @Basic
    @Column(name = "product_description")
    private String description;
    @Basic
    @Column(name = "product_release_date")
    private Date releaseDate;
    @Basic
    @Column(name = "product_picture")
    private String picture;
    @Basic
    @Column(name = "product_age_restriction")
    private String ageRestriction;

    @Basic
    @Column(name = "product_review_quantity")
    private long reviewQuantity;

    @Basic
    @Column(name = "product_estimation_quantity")
    private long estimationQuantity;

    @ManyToMany
    @JoinTable(name = "product_country", inverseJoinColumns = {@JoinColumn(name = "country_id")}, joinColumns = {@JoinColumn(name = "product_id")})
    private List<Country> countries;

    @ManyToMany
    @JoinTable(name = "product_genre", inverseJoinColumns = {@JoinColumn(name = "genre_id")}, joinColumns = {@JoinColumn(name = "product_id")})
    private List<Genre> genres;

    @ManyToOne
    @JoinColumn(name = "product_type")
    private ProductType type;

    @OneToMany
    @JoinColumn(name = "review_for")
    private List<UserReview> reviews;

    @ManyToMany
    @JoinTable(name = "product_developer", inverseJoinColumns = {@JoinColumn(name = "developer_id")}, joinColumns = {@JoinColumn(name = "product_id")})
    @MapKeyJoinColumn(name = "role_id")
    private Map<DeveloperRole, Developer> developers;



}
