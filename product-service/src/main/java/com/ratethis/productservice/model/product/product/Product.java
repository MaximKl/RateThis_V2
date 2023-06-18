package com.ratethis.productservice.model.product.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate
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

    @Basic
    @Column(name = "product_type")
    private int type;
}
