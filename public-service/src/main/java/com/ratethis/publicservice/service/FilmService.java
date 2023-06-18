package com.ratethis.publicservice.service;

import com.ratethis.publicservice.constants.Constants;
import com.ratethis.publicservice.constants.ProductTypes;
import com.ratethis.publicservice.dto.FilmDTO;
import com.ratethis.publicservice.dto.productdto.ProductDTO;
import com.ratethis.publicservice.dto.mapper.FilmDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDTOMapper;
import com.ratethis.publicservice.exception.ProductNotFoundException;
import com.ratethis.publicservice.repository.ProductFilmRepository;
import com.ratethis.publicservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ratethis.publicservice.constants.ProductTypes.FILM_NAME;

@Service
public class FilmService {

    private final ProductFilmRepository filmRepository;

    private final ProductRepository productRepository;

    private final FilmDTOMapper filmMapper;

    private final ProductDTOMapper productMapper;

    @Autowired
    public FilmService(ProductFilmRepository filmRepository, ProductRepository productRepository, FilmDTOMapper filmMapper, ProductDTOMapper productMapper) {
        this.filmRepository = filmRepository;
        this.productRepository = productRepository;
        this.filmMapper = filmMapper;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllFilms() {
        return productRepository.findByTypeId(ProductTypes.FILM).stream().map(productMapper).toList();
    }

    public FilmDTO getFilm(String id) {
        if (Constants.isNumberCheck(id)) {
            return filmRepository.findById(Long.parseLong(id)).map(filmMapper)
                    .orElseThrow(() -> new ProductNotFoundException(FILM_NAME, id));
        }
        throw new ProductNotFoundException(FILM_NAME, id);
    }
}
