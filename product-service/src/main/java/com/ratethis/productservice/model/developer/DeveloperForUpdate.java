package com.ratethis.productservice.model.developer;

import com.ratethis.productservice.model.country.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "developer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate(value = true)
public class DeveloperForUpdate {
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

    @ManyToOne
    @JoinColumn(name = "developer_country")
    private Country country;

    @ManyToMany
    @JoinTable(name = "developer_to_role", inverseJoinColumns = {@JoinColumn(name = "role_id")}, joinColumns = {@JoinColumn(name = "developer_id")})
    private List<DeveloperRole> roles;


}
