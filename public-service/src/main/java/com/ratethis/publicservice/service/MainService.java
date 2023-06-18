package com.ratethis.publicservice.service;

import com.ratethis.publicservice.constants.ProductTypes;
import com.ratethis.publicservice.dto.mapper.productmap.MainPageProductDTOMapper;
import com.ratethis.publicservice.dto.productdto.MainPageProductDTO;
import com.ratethis.publicservice.model.Product;
import com.ratethis.publicservice.properties.MainProperties;
import com.ratethis.publicservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MainService {

    private final ProductRepository repository;

    private final MainPageProductDTOMapper productMapper;

    private final MainProperties properties;

    public List<MainPageProductDTO> getMainPageProducts() {
        List<Product> products = new ArrayList<>();
        products.addAll(repository.findByTypeIdOrderByEstimationQuantityDesc(ProductTypes.GAME, Pageable.ofSize(properties.getPageSize())));
        products.addAll(repository.findByTypeIdOrderByEstimationQuantityDesc(ProductTypes.BOOK, Pageable.ofSize(properties.getPageSize())));
        products.addAll(repository.findByTypeIdOrderByEstimationQuantityDesc(ProductTypes.FILM, Pageable.ofSize(properties.getPageSize())));
        return products.stream().map(productMapper).toList();
    }
}
