package com.ratethis.publicservice.service;

import com.ratethis.publicservice.constants.Constants;
import com.ratethis.publicservice.constants.ProductTypes;
import com.ratethis.publicservice.dto.GameDTO;
import com.ratethis.publicservice.dto.productdto.ProductDTO;
import com.ratethis.publicservice.dto.mapper.GameDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductDTOMapper;
import com.ratethis.publicservice.exception.ProductNotFoundException;
import com.ratethis.publicservice.repository.ProductGameRepository;
import com.ratethis.publicservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ratethis.publicservice.constants.ProductTypes.GAME_NAME;

@Service
public class GameService {

    private final ProductGameRepository gameRepository;

    private final ProductRepository productRepository;

    private final GameDTOMapper gameMapper;

    private final ProductDTOMapper productMapper;

    @Autowired
    public GameService(ProductGameRepository gameRepository, ProductRepository productRepository, GameDTOMapper gameMapper, ProductDTOMapper productMapper) {
        this.gameRepository = gameRepository;
        this.productRepository = productRepository;
        this.gameMapper = gameMapper;
        this.productMapper = productMapper;
    }


    public List<ProductDTO> getAllGames() {
        return productRepository.findByTypeId(ProductTypes.GAME).stream().map(productMapper).toList();
    }

    public GameDTO getGame(String id) {
        if (Constants.isNumberCheck(id)) {
            return gameRepository.findById(Long.parseLong(id)).map(gameMapper)
                    .orElseThrow(()->new ProductNotFoundException(GAME_NAME, id));
        }
        throw new ProductNotFoundException(GAME_NAME, id);
    }


}
