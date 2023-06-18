package com.ratethis.publicservice.service;

import com.ratethis.publicservice.dto.mapper.developermap.ProductForDeveloperMapper;
import com.ratethis.publicservice.dto.mapper.developermap.UniqueDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.exception.ProductNotFoundException;
import com.ratethis.publicservice.model.Country;
import com.ratethis.publicservice.model.Developer;
import com.ratethis.publicservice.repository.DeveloperRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DeveloperServiceTest {
    @Mock
    private DeveloperRepository developerRepository;

    private DeveloperService underTest;

    @BeforeEach()
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ProductDeveloperDTOMapper productDeveloperDTOMapper = new ProductDeveloperDTOMapper();
        ProductForDeveloperMapper productForDeveloperMapper = new ProductForDeveloperMapper(productDeveloperDTOMapper);
        UniqueDeveloperDTOMapper developerDTOMapper = new UniqueDeveloperDTOMapper(productForDeveloperMapper);
        underTest = new DeveloperService(developerRepository, developerDTOMapper);
        Mockito.when(developerRepository.findById(1L)).thenReturn(Optional.of(new Developer(1, "oleg", "photo", "desc", new Date(), new Country(1, "UA"), new HashMap<>())));
    }

    @Test
    @DisplayName("Should retrieve developer")
    void canGetDeveloper() {
        Assertions.assertNotNull(underTest.getDeveloper("1"));
    }


    @Test()
    @DisplayName("Cannot retrieve developer")
    void cannotGetDeveloper() {
        assertThrows(ProductNotFoundException.class, () -> underTest.getDeveloper("ASVDF"));
    }
}
