package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.model.*;
import com.ratethis.publicservice.repository.*;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductFilmRepository filmRepository;
    @MockBean
    private ProductGameRepository gameRepository;
    @MockBean
    private ProductBookRepository bookRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ProfileGameRepository userGameRepository;
    @MockBean
    private ProfileFilmRepository userFilmRepository;
    @MockBean
    private ProfileBookRepository userBookRepository;

    @BeforeEach
    void setUp() {
        Product film = new Product(1, "film", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        Product game = new Product(2, "game", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        Product book = new Product(3, "book", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        UserProfile user = new UserProfile(1, "oleg", "1234", "email@gmail.com", "опис", null, null, 0, "image", new ArrayList<>(), new UserRole(1, "role"), new UserColor(1, "color"), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Mockito.when(filmRepository.findById(1L)).thenReturn(Optional.of(new ProductFilm(1, film, 0, 0, 0, 0, 0)));
        Mockito.when(gameRepository.findById(2L)).thenReturn(Optional.of(new ProductGame(2, game, 0, 0, 0, 0, 0, new ArrayList<>())));
        Mockito.when(bookRepository.findById(3L)).thenReturn(Optional.of(new ProductBook(3, book, 0, 0, 0, 0)));
        Mockito.when(userRepository.findByNick("oleg")).thenReturn(Optional.of(user));
        Mockito.when(userFilmRepository.findByProfileEntityNickAndProductId("oleg", 1)).thenReturn(Optional.of(new ProfileFilm(1,1,user,film , 0, 0, 0, 0, 0,null)));
        Mockito.when(userGameRepository.findByProfileEntityNickAndProductId("oleg", 2)).thenReturn(Optional.of(new ProfileGame(1,2,user,game, 0, 0, 0, 0,0,0,null)));
        Mockito.when(userBookRepository.findByProfileEntityNickAndProductId("oleg", 3)).thenReturn(Optional.of(new ProfileBook(1,3,user,book, 0, 0, 0, 0,null)));
    }

    @Test
    @DisplayName("Should retrieve user profile")
    void canGetUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/user/oleg"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Cannot retrieve user profile")
    void cannotGetUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/user/oleg3000"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Should retrieve specific user book review")
    void canGetBookReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/user/oleg/book/3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Cannot retrieve specific user book review")
    void cannotGetBookReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/user/oleg/book/28"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Should retrieve specific user game review")
    void canGetGameReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/user/oleg/game/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Cannot retrieve specific user game review")
    void cannotGetGameReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/user/oleg456/game/3487"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Should retrieve specific user film review")
    void canGetFilmReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/user/oleg/film/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Cannot retrieve specific user film review")
    void cannotGetFilmReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/user/oleg/film/7d8sffg4"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
