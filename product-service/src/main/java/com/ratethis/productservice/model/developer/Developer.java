package com.ratethis.productservice.model.developer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Entity
@Table(name = "developer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate(value = true)
public class Developer {

    @Id
    @Column(name = "developer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "developer_name")
    private String name;

    @Column(name = "developer_photo")
    private String photo;

    @Column(name = "developer_description")
    private String description;

    @Column(name = "developer_birthday")
    private Date birthday;

    @Column(name = "developer_country")
    private int country;

}
