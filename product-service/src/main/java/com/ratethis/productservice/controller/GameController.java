package com.ratethis.productservice.controller;

import com.ratethis.productservice.dto.ProfileGameDTO;
import com.ratethis.productservice.model.product.profile.ProfileGame;
import com.ratethis.productservice.service.GameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/RateThis-product/game")
public class GameController {

    private final GameService gameService;

    @PostMapping("/saveProfileEstimates")
    public ResponseEntity<ProfileGameDTO> saveEstimates(@RequestBody @Valid ProfileGame profileGame, @CookieValue(name = "token") String token) {
        return new ResponseEntity<>(gameService.saveEstimates(profileGame, token), HttpStatus.CREATED);
    }

    @GetMapping("/userEstimates/{gameId}")
    public ResponseEntity<ProfileGameDTO> getEstimates(@PathVariable(name = "gameId") String gameId, @CookieValue(name = "token") String token) {
        return new ResponseEntity<>(gameService.getPersonalEstimates(gameId, token), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/userEstimates/{gameId}")
    public ResponseEntity removeEstimates(@PathVariable(name = "gameId") String gameId, @CookieValue(name = "token") String token) {
        gameService.removePersonalEstimates(gameId, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



}
