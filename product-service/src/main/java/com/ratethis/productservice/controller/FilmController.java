package com.ratethis.productservice.controller;

import com.ratethis.productservice.dto.ProfileFilmDTO;
import com.ratethis.productservice.model.product.profile.ProfileFilm;
import com.ratethis.productservice.service.FilmService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/RateThis-product/film")
public class FilmController {

    private final FilmService filmService;

    @PostMapping("/saveProfileEstimates")
    public ResponseEntity<ProfileFilmDTO> saveEstimates(@RequestBody @Valid ProfileFilm profileFilm, @CookieValue(name = "token") String token) {
        return new ResponseEntity<>(filmService.saveEstimates(profileFilm, token), HttpStatus.CREATED);
    }

    @GetMapping("/userEstimates/{filmId}")
    public ResponseEntity<ProfileFilmDTO> getEstimates(@PathVariable(name = "filmId") String filmId, @CookieValue(name = "token") String token) {
        return new ResponseEntity<>(filmService.getPersonalEstimates(filmId, token), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/userEstimates/{filmId}")
    public ResponseEntity removeEstimates(@PathVariable(name = "filmId") String filmId, @CookieValue(name = "token") String token) {
        filmService.removePersonalEstimates(filmId, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
