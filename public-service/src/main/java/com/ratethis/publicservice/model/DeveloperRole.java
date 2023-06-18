package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Random;

@Entity
@Table(name = "developer_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeveloperRole {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role_name")
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

