package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.model.*;
import com.ratethis.publicservice.repository.ProductBookRepository;
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

import static com.ratethis.publicservice.constants.ProductTypes.BOOK;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductBookRepository bookRepository;
    @MockBean
    private ProductRepository productRepository;
    @BeforeEach
    void setUp() {
        Product book = new Product(3, "book", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        Mockito.when(bookRepository.findById(3L)).thenReturn(Optional.of(new ProductBook(3, book, 0, 0, 0, 0)));
        Mockito.when(productRepository.findByTypeId(BOOK)).thenReturn(List.of(book,book));
    }

    @Test
    @DisplayName("Should retrieve all book")
    void canGetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/books"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Should retrieve specific book")
    void canGetBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/books/3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Cannot retrieve specific book")
    void cannotGetBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/books/dgf56f"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
