package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.dto.FilmDTO;
import com.ratethis.publicservice.dto.productdto.ProductDTO;
import com.ratethis.publicservice.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/RateThis/public")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public List<ProductDTO> getAllFilms() {
        return filmService.getAllFilms();
    }
    @GetMapping("/films/{id}")
    public FilmDTO getFilm(@PathVariable(name = "id") String id) {
        return filmService.getFilm(id);
    }

}
