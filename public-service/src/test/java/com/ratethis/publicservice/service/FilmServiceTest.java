package com.ratethis.publicservice.service;

import com.ratethis.publicservice.dto.mapper.FilmDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.exception.ProductNotFoundException;
import com.ratethis.publicservice.model.Product;
import com.ratethis.publicservice.model.ProductFilm;
import com.ratethis.publicservice.model.ProductType;
import com.ratethis.publicservice.repository.ProductFilmRepository;
import com.ratethis.publicservice.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.ratethis.publicservice.constants.ProductTypes.FILM;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmServiceTest {
    @Mock
    private ProductFilmRepository filmRepository;
    @Mock
    private ProductRepository productRepository;
    private FilmService underTest;

    @BeforeEach()
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ProductDeveloperDTOMapper developerDTOMapper = new ProductDeveloperDTOMapper();
        ProductReviewDTOMapper reviewDTOMapper = new ProductReviewDTOMapper();
        ProductDTOMapper productMapper = new ProductDTOMapper(developerDTOMapper);
        FilmDTOMapper filmMapper = new FilmDTOMapper(reviewDTOMapper, developerDTOMapper);
        underTest = new FilmService(filmRepository,productRepository,filmMapper,productMapper);

        Product product = new Product(1, "film", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        ProductFilm productFilm = new ProductFilm(1, product, 0, 0, 0, 0, 0);
        Mockito.when(filmRepository.findById(1L)).thenReturn(Optional.of(productFilm));
        Mockito.when(productRepository.findByTypeId(FILM)).thenReturn(List.of(product,product));
    }

    @Test
    @DisplayName("Should retrieve specific game")
    void canGetSpecificFilm() {
        Assertions.assertNotNull(underTest.getFilm("1"));
    }
    @Test
    @DisplayName("Cannot retrieve specific game")
    void cannotGetSpecificFilm() {
        assertThrows(ProductNotFoundException.class, () ->underTest.getFilm("sdfsd4545"));
    }
    @Test
    @DisplayName("Should retrieve all films")
    void canGetAllFilms() {
        Assertions.assertFalse(underTest.getAllFilms().isEmpty());
    }
}
