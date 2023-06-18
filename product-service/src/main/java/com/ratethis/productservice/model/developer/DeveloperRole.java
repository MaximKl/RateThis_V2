package com.ratethis.productservice.model.developer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "developer_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate(value = true)
public class DeveloperRole {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name")
    @NotBlank(message = "Developer role can't be empty")
    @Length(min = 2, max = 200, message = "Developer role must be from 2 to 200 symbols")
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperRole that = (DeveloperRole) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        long randomNumber = Math.round(Math.random() * 100000);
        return Objects.hash(id + randomNumber, name);
    }
}

