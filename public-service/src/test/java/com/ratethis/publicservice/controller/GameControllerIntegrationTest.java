package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.model.*;
import com.ratethis.publicservice.repository.ProductGameRepository;
import com.ratethis.publicservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.*;

import static com.ratethis.publicservice.constants.ProductTypes.GAME;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductGameRepository gameRepository;
    @MockBean
    private ProductRepository productRepository;
    @BeforeEach
    void setUp() {
        Product game = new Product(2, "game", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        Mockito.when(gameRepository.findById(2L)).thenReturn(Optional.of(new ProductGame(2, game, 0, 0, 0, 0,0,new ArrayList<>())));
        Mockito.when(productRepository.findAll()).thenReturn(List.of(game, game));
        Mockito.when(productRepository.findByTypeId(GAME)).thenReturn(List.of(game,game));
    }
    @Test
    @DisplayName("Should retrieve all games")
    void canGetAllGames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/games"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Should retrieve specific game")
    void canGetGame() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/games/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Cannot retrieve specific game")
    void cannotGetGame() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/games/2fgj454"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
