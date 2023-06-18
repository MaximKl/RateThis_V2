package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "type_id")
    private long id;
    @Basic
    @Column(name = "type_name")
    private String name;

}
