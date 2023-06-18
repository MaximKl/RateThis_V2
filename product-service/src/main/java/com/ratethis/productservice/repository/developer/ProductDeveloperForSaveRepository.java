package com.ratethis.productservice.repository.developer;

import com.ratethis.productservice.model.developer.ProductDeveloperForSave;
import org.springframework.data.repository.CrudRepository;

public interface ProductDeveloperForSaveRepository extends CrudRepository<ProductDeveloperForSave, Long> {

    void deleteAllByProductId(long productId);

}
