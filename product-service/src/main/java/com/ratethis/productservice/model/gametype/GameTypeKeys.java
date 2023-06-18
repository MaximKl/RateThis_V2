package com.ratethis.productservice.model.gametype;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "game_to_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(GameTypeKeysID.class)
@DynamicUpdate(value = true)
public class GameTypeKeys {
    @Id
    @Column(name = "game_id")
    private long productId;
    @Id
    @Column(name = "type_id")
    private int gameTypeId;
}
