package com.ratethis.publicservice.controller;

import com.ratethis.publicservice.constants.ProductTypes;
import com.ratethis.publicservice.dto.productdto.ProductReviewDTO;
import com.ratethis.publicservice.dto.userdto.*;
import com.ratethis.publicservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "RateThis/public")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{nick}")
    public ProfileDTO getUserPublicInfo(@PathVariable(name = "nick") String nick) {
        return userService.getUser(nick);
    }

    @GetMapping("/userFriends/{nick}")
    public List<FriendDTO> getFriends(@PathVariable(name = "nick") String nick) {
        return userService.getUserFriends(nick);
    }

    @GetMapping("/userLightFriends/{nick}")
    public List<LightFriendDTO> getUnapprovedFriends(@PathVariable(name = "nick") String nick) {
        return userService.getUserUnapprovedFriends(nick);
    }

    @GetMapping("/userFriendSuggestion/{nick}")
    public List<FriendDTO> getFriendsSuggestions(@PathVariable(name = "nick") String nick) {
        return userService.getUsersFriendSuggestion(nick);
    }

    @GetMapping("/user/{nick}/films")
    public List<UserFilmDTO> getUserProfileFilms(@PathVariable(name = "nick") String nick) {
        return userService.getUserFilms(nick, ProductTypes.FILM);
    }
    @GetMapping("/user/{nick}/books")
    public List<UserBookDTO> getUserProfileBooks(@PathVariable(name = "nick") String nick) {
        return userService.getUserBooks(nick, ProductTypes.BOOK);
    }
    @GetMapping("/user/{nick}/games")
    public List<UserGameDTO> getUserProfileGames(@PathVariable(name = "nick") String nick) {
        return userService.getUserGames(nick, ProductTypes.GAME);
    }

    @GetMapping("/user/{nick}/books/{id}")
    public UserFullBookDTO getBookReview(@PathVariable(name = "nick") String nick, @PathVariable(name = "id") String id) {
        return userService.getUserBookReview(nick, id);
    }

    @GetMapping("/user/{nick}/games/{id}")
    public UserFullGameDTO getGameReview(@PathVariable(name = "nick") String nick, @PathVariable(name = "id") String id) {
        return userService.getUserGameReview(nick, id);
    }

    @GetMapping("/user/{nick}/films/{id}")
    public UserFullFilmDTO getFilmReview(@PathVariable(name = "nick") String nick, @PathVariable(name = "id") String id) {
        return userService.getUserFilmReview(nick, id);
    }

    @GetMapping("/productReviews/{productId}")
    public List<ProductReviewDTO> getReviews(@PathVariable(value = "productId")String id){
        return userService.getProductReviews(id);
    }

}
