package com.ratethis.productservice.model.developer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "developer_to_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(DeveloperToRoleID.class)
@DynamicUpdate(value = true)
public class DeveloperToRole {
    @Id
    @Column(name = "developer_id")
    private int developer;

    @Id
    @Column(name = "role_id")
    private int role;
}
