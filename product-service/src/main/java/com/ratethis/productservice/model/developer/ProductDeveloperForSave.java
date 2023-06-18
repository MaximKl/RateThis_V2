package com.ratethis.productservice.model.developer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "product_developer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ProductDeveloperID.class)
@DynamicUpdate(value = true)
public class ProductDeveloperForSave {

    @Id
    @Column(name = "product_id")
    private long productId;
    @Id
    @Column(name = "role_id")
    private int developerRole;

    @Id
    @Column(name = "developer_id")
    private int developer;

}
