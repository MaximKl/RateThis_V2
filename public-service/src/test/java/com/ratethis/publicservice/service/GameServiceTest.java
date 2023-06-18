package com.ratethis.publicservice.service;

import com.ratethis.publicservice.dto.mapper.GameDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.exception.ProductNotFoundException;
import com.ratethis.publicservice.model.*;
import com.ratethis.publicservice.repository.ProductGameRepository;
import com.ratethis.publicservice.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.ratethis.publicservice.constants.ProductTypes.GAME;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameServiceTest {
    @Mock
    private ProductGameRepository gameRepository;

    @Mock
    private ProductRepository productRepository;

    private GameService underTest;


    @BeforeEach()
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ProductDeveloperDTOMapper developerDTOMapper = new ProductDeveloperDTOMapper();
        ProductReviewDTOMapper reviewDTOMapper = new ProductReviewDTOMapper();
        ProductDTOMapper productMapper = new ProductDTOMapper(developerDTOMapper);
        GameDTOMapper gameMapper = new GameDTOMapper(reviewDTOMapper, developerDTOMapper);
        underTest = new GameService(gameRepository,productRepository,gameMapper,productMapper);

        Product product = new Product(2, "game", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        ProductGame productGame = new ProductGame(2, product, 0, 0, 0, 0, 0, new ArrayList<>());
        Mockito.when(gameRepository.findById(2L)).thenReturn(Optional.of(productGame));
        Mockito.when(productRepository.findByTypeId(GAME)).thenReturn(List.of(product,product));
    }


    @Test
    @DisplayName("Should retrieve specific game")
    void canGetSpecificGame() {
        Assertions.assertNotNull(underTest.getGame("2"));
    }
    @Test
    @DisplayName("Cannot retrieve specific game")
    void cannotGetSpecificGame() {
        assertThrows(ProductNotFoundException.class, () ->underTest.getGame("sdf3e5"));
    }

    @Test
    @DisplayName("Should retrieve all games")
    void canGetAllGames() {
        Assertions.assertFalse(underTest.getAllGames().isEmpty());
    }


}
