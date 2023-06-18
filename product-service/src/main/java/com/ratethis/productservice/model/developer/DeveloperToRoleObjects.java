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
public class DeveloperToRoleObjects {

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private DeveloperRole role;

    @Id
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

}
