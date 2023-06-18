package com.ratethis.productservice.model.developer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DeveloperToRoleID implements Serializable {
    private int developer;
    private int role;
}
