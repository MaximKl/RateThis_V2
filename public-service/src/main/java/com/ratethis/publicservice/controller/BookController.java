package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.dto.BookDTO;
import com.ratethis.publicservice.dto.productdto.ProductDTO;
import com.ratethis.publicservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/RateThis/public")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<ProductDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/books/{id}")
    public BookDTO getBook(@PathVariable(name = "id") String id) {
        return bookService.getBook(id);
    }

}
