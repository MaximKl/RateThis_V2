package com.ratethis.productservice.service;

import com.ratethis.productservice.constants.Constants;
import com.ratethis.productservice.dto.DeveloperDTO;
import com.ratethis.productservice.dto.DeveloperForSaveDTO;
import com.ratethis.productservice.dto.DeveloperForUpdateDTO;
import com.ratethis.productservice.dto.ProductForSaveDTO;
import com.ratethis.productservice.dto.mapper.DeveloperForSaveDTOMapper;
import com.ratethis.productservice.dto.mapper.DeveloperForUpdateDTOMapper;
import com.ratethis.productservice.dto.mapper.DeveloperRoleDTOMapper;
import com.ratethis.productservice.dto.mapper.ProductForSaveDTOMapper;
import com.ratethis.productservice.exception.IdentificationException;
import com.ratethis.productservice.exception.TypeErrorException;
import com.ratethis.productservice.model.country.Country;
import com.ratethis.productservice.model.country.CountryKeys;
import com.ratethis.productservice.model.developer.Developer;
import com.ratethis.productservice.model.developer.DeveloperRole;
import com.ratethis.productservice.model.developer.DeveloperToRole;
import com.ratethis.productservice.model.developer.ProductDeveloperForSave;
import com.ratethis.productservice.model.gametype.GameType;
import com.ratethis.productservice.model.gametype.GameTypeKeys;
import com.ratethis.productservice.model.genre.Genre;
import com.ratethis.productservice.model.genre.GenreKeys;
import com.ratethis.productservice.model.product.product.Product;
import com.ratethis.productservice.model.product.product.ProductBook;
import com.ratethis.productservice.model.product.product.ProductFilm;
import com.ratethis.productservice.repository.country.CountryKeysRepository;
import com.ratethis.productservice.repository.country.CountryRepository;
import com.ratethis.productservice.repository.developer.*;
import com.ratethis.productservice.repository.gametype.GameTypeKeysRepository;
import com.ratethis.productservice.repository.gametype.GameTypeRepository;
import com.ratethis.productservice.repository.genre.GenreKeysRepository;
import com.ratethis.productservice.repository.genre.GenreRepository;
import com.ratethis.productservice.repository.product.ProductBookRepository;
import com.ratethis.productservice.repository.product.ProductFilmRepository;
import com.ratethis.productservice.repository.product.ProductRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.ratethis.productservice.constants.Constants.*;

@Service
@AllArgsConstructor
public class AdminService {

    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final GameTypeRepository gameTypeRepository;
    private final DeveloperToRoleObjectsRepository developerToRoleObjectsRepository;
    private final ProductRepository productRepository;
    private final GameTypeKeysRepository gameTypeKeysRepository;
    private final CountryKeysRepository countryKeysRepository;
    private final GenreKeysRepository genreKeysRepository;
    private final ProductDeveloperForSaveRepository developerForSaveRepository;
    private final DeveloperRoleRepository roleRepository;
    private final DeveloperRepository developerRepository;

    private final DeveloperForUpdateRepository developerForUpdateRepository;

    private final DeveloperToRoleRepository developerToRoleRepository;

    private final ProductFilmRepository productFilmRepository;
    private final ProductBookRepository productBookRepository;
    private final ProductForSaveDTOMapper productForSaveDTOMapper;
    private final DeveloperRoleDTOMapper developerRoleDTOMapper;
    private final DeveloperForSaveDTOMapper developerForSaveDTOMapper;
    private final DeveloperForUpdateDTOMapper developerForUpdateDTOMapper;


    public List<Genre> getAllGenres() {
        return genreRepository.findAllBy();
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAllBy();
    }

    public List<GameType> getAllGameTypes() {
        return gameTypeRepository.findAllBy();
    }

    public SortedMap<String, Set<DeveloperDTO>> getAllDevelopers() {
        return developerRoleDTOMapper.apply(developerToRoleObjectsRepository.findAllBy());
    }

    @Transactional
    public void saveProduct(ProductForSaveDTO product) {
        if (!product.getType().equals("book") && !product.getType().equals("game") && !product.getType().equals("film"))
            throw new ValidationException();

        Product savedProduct;

        if (product.getId() > 0) {
            Product oldProduct = productRepository.findById(product.getId()).orElseThrow(ValidationException::new);
            savedProduct = productRepository.save(productForSaveDTOMapper.apply(product, oldProduct));
        } else {
            savedProduct = productRepository.save(productForSaveDTOMapper.apply(product, null));
        }

        if (savedProduct.getPicture().isBlank()) {
            savedProduct.setPicture(Constants.saveProductPicture(product.getPicture(), savedProduct.getId()));
            productRepository.save(savedProduct);
        }

        if (product.getType().equals("game")) {
            if (product.getGameTypes().isEmpty())
                throw new ValidationException();
            if (product.getId() > 0) {
                gameTypeKeysRepository.deleteAllByProductId(product.getId());
            }
            product.getGameTypes().forEach(gt -> gameTypeKeysRepository.save(new GameTypeKeys(savedProduct.getId(), gt)));
        }

        if (product.getType().equals("book")) {
            ProductBook book = productBookRepository.findById(savedProduct.getId()).orElseThrow(ValidationException::new);
            book.setBookSize(parseSize(product.getSize()));
            productBookRepository.save(book);
        }

        if (product.getType().equals("film")) {
            ProductFilm film = productFilmRepository.findById(savedProduct.getId()).orElseThrow(ValidationException::new);
            film.setTime(parseSize(product.getSize()));
            productFilmRepository.save(film);
        }

        if (product.getId() > 0) {
            countryKeysRepository.deleteAllByProductId(product.getId());
            genreKeysRepository.deleteAllByProductId(product.getId());
            developerForSaveRepository.deleteAllByProductId(product.getId());
        }

        product.getCountries().forEach(c -> countryKeysRepository.save(new CountryKeys(savedProduct.getId(), c)));
        product.getGenres().forEach(g -> genreKeysRepository.save(new GenreKeys(savedProduct.getId(), g)));


        Map<String, List<Integer>> developers = new HashMap<>();
        product.getDevelopers().forEach(o -> developers.put((String) o.get(0), (List<Integer>) o.get(1)));

        developers.forEach((k, v) -> {
            DeveloperRole role = roleRepository.findDeveloperRoleByName(k).orElseThrow(ValidationException::new);
            v.forEach(dev ->
                    developerForSaveRepository.save(new ProductDeveloperForSave(savedProduct.getId(), role.getId(), dev))
            );
        });
    }

    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public void saveGameType(GameType gameType) {
        gameTypeRepository.save(gameType);
    }

    private int parseSize(String size) {
        int returnSize = 0;
        try {
            returnSize = Integer.parseInt(size);
        } catch (NumberFormatException e) {
            throw new TypeErrorException();
        }
        return returnSize;
    }

    public List<DeveloperRole> getAllDevelopersRoles() {
        return roleRepository.findAllBy();
    }

    public void saveRole(DeveloperRole role) {
        roleRepository.save(role);
    }

    @Transactional
    public void saveDeveloper(DeveloperForSaveDTO developer) {
        Developer savedDeveloper = developerRepository.save(developerForSaveDTOMapper.apply(developer));
        if (savedDeveloper.getPhoto().isBlank()) {
            savedDeveloper.setPhoto(saveDeveloperPicture(developer.getPhoto(), savedDeveloper.getId()));
            developerRepository.save(savedDeveloper);
        }
        if (developer.getId() > 0)
            developerToRoleRepository.deleteDeveloperToRoleByDeveloper(developer.getId());
        developer.getRoles().forEach(r -> developerToRoleRepository.save(new DeveloperToRole(savedDeveloper.getId(), r)));
    }

    public DeveloperForUpdateDTO getDeveloper(String developerId) {
        if (!isNumberCheck(developerId))
            throw new TypeErrorException();
        return developerForUpdateRepository.findById(Integer.parseInt(developerId)).map(developerForUpdateDTOMapper).orElseThrow(ValidationException::new);
    }

    @Transactional
    public void deleteProduct(String productId) {
        if (!isNumberCheck(productId))
            throw new TypeErrorException();
        productRepository.deleteById(Long.parseLong(productId));
    }

    @Transactional
    public void deleteDeveloper(String developerId) {
        if (!isNumberCheck(developerId))
            throw new TypeErrorException();
        developerRepository.deleteDeveloperById(Integer.parseInt(developerId));
    }

    @Transactional
    public void deleteReview(String name, String productId, String token) {
        if (!isNumberCheck(productId))
            throw new TypeErrorException();

        if (!userNameIdentification(token).equals(name))
            throw new IdentificationException();

        productRepository.removeReview(name, Long.parseLong(productId));
    }
}
