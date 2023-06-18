package com.ratethis.productservice.repository.product;

import com.ratethis.productservice.model.product.product.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Modifying
    @Query(value = "call remove_review(:nick,:product_id)", nativeQuery = true)
    void removeReview(@Param("nick") String nick, @Param("product_id") long productId);

}
