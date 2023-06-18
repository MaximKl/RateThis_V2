package com.ratethis.productservice.model.country;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_country")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(CountryKeysID.class)
@DynamicUpdate(value = true)
public class CountryKeys {

    @Id
    @Column(name = "product_id")
    private long productId;

    @Id
    @Column(name = "country_id")
    private int countryId;

}
