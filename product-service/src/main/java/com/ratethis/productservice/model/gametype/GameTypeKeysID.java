package com.ratethis.productservice.model.gametype;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GameTypeKeysID implements Serializable {

    private long productId;

    private int gameTypeId;
}
