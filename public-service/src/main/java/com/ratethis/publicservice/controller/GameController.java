package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.dto.GameDTO;
import com.ratethis.publicservice.dto.productdto.ProductDTO;
import com.ratethis.publicservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/RateThis/public")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public List<ProductDTO> getAllGames() {
        return gameService.getAllGames();
    }
    @GetMapping("/games/{id}")
    public GameDTO getGame(@PathVariable(name = "id") String id) {
        return gameService.getGame(id);
    }


}
