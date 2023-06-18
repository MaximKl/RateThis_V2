package com.ratethis.productservice.model.product.profile;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfileGameID implements Serializable {

    private long profileId;
    private long productId;
}
