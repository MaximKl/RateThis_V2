package com.ratethis.productservice.repository.product;

import com.ratethis.productservice.model.product.product.ProductBook;
import org.springframework.data.repository.CrudRepository;

public interface ProductBookRepository extends CrudRepository<ProductBook,Long> {
}
