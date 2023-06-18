package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.Developer;
import com.ratethis.publicservice.repository.DeveloperRepository;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeveloperControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeveloperRepository developerRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(developerRepository.findById(1L)).thenReturn(Optional.of(new Developer(1, "oleg", "photo", "desc", new Date(), new Country(1, "UA"), new HashMap<>())));
    }

    @Test
    @DisplayName("Should retrieve developer")
    void canGetDeveloper() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/entity/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test()
    @DisplayName("Cannot retrieve developer")
    void cannotGetDeveloper() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RateThis/public/entity/dsf435dd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
