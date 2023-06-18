package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.model.*;
import com.ratethis.publicservice.repository.ProductFilmRepository;
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

import static com.ratethis.publicservice.constants.ProductTypes.FILM;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductFilmRepository filmRepository;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product film = new Product(1, "film", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        Mockito.when(filmRepository.findById(1L)).thenReturn(Optional.of(new ProductFilm(1, film, 0, 0, 0, 0,0)));
        Mockito.when(productRepository.findByTypeId(FILM)).thenReturn(List.of(film,film));
    }

    @Test
    @DisplayName("Should retrieve all games")
    void canGetAllFilms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/films"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Should retrieve specific game")
    void canGetFilm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/films/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Cannot retrieve all games")
    void cannotGetFilm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/films/h46h54b6"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


}
