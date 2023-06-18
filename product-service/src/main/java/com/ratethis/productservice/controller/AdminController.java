package com.ratethis.productservice.controller;

import com.ratethis.productservice.authentication.Authenticate;
import com.ratethis.productservice.dto.DeveloperDTO;
import com.ratethis.productservice.dto.DeveloperForSaveDTO;
import com.ratethis.productservice.dto.DeveloperForUpdateDTO;
import com.ratethis.productservice.dto.ProductForSaveDTO;
import com.ratethis.productservice.model.*;
import com.ratethis.productservice.model.country.Country;
import com.ratethis.productservice.model.developer.DeveloperRole;
import com.ratethis.productservice.model.gametype.GameType;
import com.ratethis.productservice.model.genre.Genre;
import com.ratethis.productservice.service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/RateThis-product")
public class AdminController {

    private final AdminService adminService;

    @Authenticate(role = UserRole.ADMIN)
    @GetMapping("/genres")
    public List<Genre> getAllGenres(@CookieValue(name = "token") String token) {
        return adminService.getAllGenres();
    }

    @Authenticate(role = UserRole.ADMIN)
    @GetMapping("/countries")
    public List<Country> getAllCountries(@CookieValue(name = "token") String token) {
        return adminService.getAllCountries();
    }

    @Authenticate(role = UserRole.ADMIN)
    @GetMapping("/game-types")
    public List<GameType> getAllGameTypes(@CookieValue(name = "token") String token) {
        return adminService.getAllGameTypes();
    }

    @Authenticate(role = UserRole.ADMIN)
    @GetMapping("/developers")
    public SortedMap<String, Set<DeveloperDTO>> getAllDevelopers(@CookieValue(name = "token") String token) {
        return adminService.getAllDevelopers();
    }

    @Authenticate(role = UserRole.ADMIN)
    @GetMapping("/developers/{devId}")
    public DeveloperForUpdateDTO getAllDevelopers(@PathVariable(name = "devId") String developerId, @CookieValue(name = "token") String token) {
        return adminService.getDeveloper(developerId);
    }

    @Authenticate(role = UserRole.ADMIN)
    @GetMapping("/developers-roles")
    public List<DeveloperRole> getAllDeveloperRoles(@CookieValue(name = "token") String token) {
        return adminService.getAllDevelopersRoles();
    }

    @Authenticate(role = UserRole.ADMIN)
    @PostMapping("/save-product")
    public ResponseEntity receiveProduct(@RequestBody @Valid ProductForSaveDTO product, @CookieValue(name = "token") String token) {
        adminService.saveProduct(product);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Authenticate(role = UserRole.ADMIN)
    @PostMapping("/genres")
    public ResponseEntity receiveGenre(@RequestBody @Valid Genre genre, @CookieValue(name = "token") String token) {
        adminService.saveGenre(genre);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Authenticate(role = UserRole.ADMIN)
    @PostMapping("/game-types")
    public ResponseEntity receiveGameType(@RequestBody @Valid GameType gameType, @CookieValue(name = "token") String token) {
        adminService.saveGameType(gameType);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Authenticate(role = UserRole.ADMIN)
    @PostMapping("/developers")
    public ResponseEntity receiveDeveloper(@RequestBody @Valid DeveloperForSaveDTO developer, @CookieValue(name = "token") String token) {
        adminService.saveDeveloper(developer);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Authenticate(role = UserRole.ADMIN)
    @PostMapping("/developers-roles")
    public ResponseEntity receiveDeveloperRole(@RequestBody @Valid DeveloperRole role,@CookieValue(name = "token") String token) {
        adminService.saveRole(role);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Authenticate(role = UserRole.ADMIN)
    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(@PathVariable(name = "productId") String productId, @CookieValue(name = "token") String token) {
        adminService.deleteProduct(productId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Authenticate(role = UserRole.ADMIN)
    @DeleteMapping("/developer/{developerId}")
    public ResponseEntity deleteDeveloper(@PathVariable(name = "developerId") String developerId, @CookieValue(name = "token") String token) {
        adminService.deleteDeveloper(developerId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/removeReview/{name}/{productId}")
    public ResponseEntity deleteFullReview(@PathVariable(name = "name") String name,@PathVariable(name = "productId") String productId, @CookieValue(name = "token") String token) {
        adminService.deleteReview(name, productId, token);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
