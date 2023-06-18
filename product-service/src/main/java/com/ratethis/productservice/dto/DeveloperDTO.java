package com.ratethis.productservice.dto;

import java.util.Date;
import java.util.Objects;

public record DeveloperDTO (
        int id,
        String name,
        Date birthday
){
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperDTO that = (DeveloperDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
