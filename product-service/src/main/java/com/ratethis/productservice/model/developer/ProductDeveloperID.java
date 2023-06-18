package com.ratethis.productservice.model.developer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDeveloperID implements Serializable {
    private long productId;
    private int developer;
    private int developerRole;
}
