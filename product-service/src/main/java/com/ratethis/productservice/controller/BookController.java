package com.ratethis.productservice.controller;

import com.ratethis.productservice.dto.ProfileBookDTO;
import com.ratethis.productservice.model.product.profile.ProfileBook;
import com.ratethis.productservice.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/RateThis-product/book")
public class BookController {

    private final BookService profileBookService;

    @PostMapping("/saveProfileEstimates")
    public ResponseEntity<ProfileBookDTO> saveEstimates(@RequestBody @Valid ProfileBook profileBook, @CookieValue(name = "token") String token) {
        return new ResponseEntity<>(profileBookService.saveEstimates(profileBook, token), HttpStatus.CREATED);
    }

    @GetMapping("/userEstimates/{bookId}")
    public ResponseEntity<ProfileBookDTO> getEstimates(@PathVariable(name = "bookId") String bookId, @CookieValue(name = "token") String token) {
        return new ResponseEntity<>(profileBookService.getPersonalEstimates(bookId, token), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/userEstimates/{bookId}")
    public ResponseEntity removeEstimates(@PathVariable(name = "bookId") String bookId, @CookieValue(name = "token") String token) {
        profileBookService.removePersonalEstimates(bookId, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



}
