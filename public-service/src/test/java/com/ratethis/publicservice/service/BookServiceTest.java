package com.ratethis.publicservice.service;

import com.ratethis.publicservice.dto.mapper.BookDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.exception.ProductNotFoundException;
import com.ratethis.publicservice.model.Product;
import com.ratethis.publicservice.model.ProductBook;
import com.ratethis.publicservice.model.ProductType;
import com.ratethis.publicservice.repository.ProductBookRepository;
import com.ratethis.publicservice.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static com.ratethis.publicservice.constants.ProductTypes.BOOK;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookServiceTest {
    @Mock
    private ProductBookRepository bookRepository;

    @Mock
    private ProductRepository productRepository;

    private BookService underTest;

    @BeforeEach()
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ProductDeveloperDTOMapper developerDTOMapper = new ProductDeveloperDTOMapper();
        ProductReviewDTOMapper reviewDTOMapper = new ProductReviewDTOMapper();
        ProductDTOMapper productMapper = new ProductDTOMapper(developerDTOMapper);
        BookDTOMapper bookDTOMapper = new BookDTOMapper(reviewDTOMapper, developerDTOMapper);
        underTest = new BookService(bookRepository, productRepository, bookDTOMapper, productMapper);

        Product product = new Product(3, "book", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        ProductBook productBook = new ProductBook(3, product, 0, 0, 0, 0);


        Mockito.when(bookRepository.findById(3L)).thenReturn(Optional.of(productBook));
        Mockito.when(productRepository.findByTypeId(BOOK)).thenReturn(List.of(product,product));
    }


    @Test
    @DisplayName("Should retrieve specific book")
    void canGetSpecificBook() {
        Assertions.assertNotNull(underTest.getBook("3"));
    }

    @Test
    @DisplayName("Cannot retrieve specific book")
    void cannotGetSpecificBook() {
        assertThrows(ProductNotFoundException.class, () -> underTest.getBook("657hjhu7"));
    }

    @Test
    @DisplayName("Should retrieve all books")
    void canGetAllBooks() {
        Assertions.assertFalse(underTest.getAllBooks().isEmpty());
    }

}
