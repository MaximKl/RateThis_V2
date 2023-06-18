package com.ratethis.publicservice.service;

import com.ratethis.publicservice.constants.Constants;
import com.ratethis.publicservice.dto.BookDTO;
import com.ratethis.publicservice.dto.productdto.ProductDTO;
import com.ratethis.publicservice.dto.mapper.BookDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDTOMapper;
import com.ratethis.publicservice.exception.ProductNotFoundException;
import com.ratethis.publicservice.repository.ProductBookRepository;
import com.ratethis.publicservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ratethis.publicservice.constants.ProductTypes.BOOK;
import static com.ratethis.publicservice.constants.ProductTypes.BOOK_NAME;

@Service
public class BookService {

    private final ProductBookRepository bookRepository;

    private final ProductRepository productRepository;

    private final BookDTOMapper bookMapper;

    private final ProductDTOMapper productMapper;

    @Autowired
    public BookService(ProductBookRepository bookRepository, ProductRepository productRepository, BookDTOMapper bookMapper, ProductDTOMapper productMapper) {
        this.bookRepository = bookRepository;
        this.productRepository = productRepository;
        this.bookMapper = bookMapper;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllBooks() {
        return productRepository.findByTypeId(BOOK).stream().map(productMapper).toList();
    }

    public BookDTO getBook(String id) {
        if (Constants.isNumberCheck(id)) {
            return bookRepository.findById(Long.parseLong(id)).map(bookMapper)
                    .orElseThrow(()->new ProductNotFoundException(BOOK_NAME, id));
        }
        throw new ProductNotFoundException(BOOK_NAME, id);
    }


}
