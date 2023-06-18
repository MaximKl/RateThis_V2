package com.ratethis.publicservice.service;

import com.ratethis.publicservice.constants.Constants;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.dto.mapper.usermap.*;
import com.ratethis.publicservice.dto.productdto.ProductReviewDTO;
import com.ratethis.publicservice.dto.userdto.*;
import com.ratethis.publicservice.exception.ProductAndProfileException;
import com.ratethis.publicservice.exception.ProfileNotFoundException;
import com.ratethis.publicservice.exception.TypeErrorException;
import com.ratethis.publicservice.model.ProfileBook;
import com.ratethis.publicservice.model.ProfileFilm;
import com.ratethis.publicservice.model.ProfileGame;
import com.ratethis.publicservice.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ratethis.publicservice.constants.ProductTypes.*;

@Service
@AllArgsConstructor
public class UserService {

    private final ProfileDTOMapper profileMapper;

    private final UserRepository userRepository;

    private final ProfileGameRepository userGameRepository;

    private final ProfileFilmRepository userFilmRepository;

    private final ProfileBookRepository userBookRepository;

    private final ProductFilmRepository filmRepository;
    private final ProductGameRepository gameRepository;

    private final ProductBookRepository bookRepository;

    private final UserFullGameDTOMapper gameMapper;

    private final UserFullFilmDTOMapper filmMapper;

    private final UserFullBookDTOMapper bookMapper;
    private final ReviewRepository reviewRepository;
    private final ProductReviewDTOMapper reviewDTOMapper;

    private final UserFilmDTOMapper filmDTOMapper;
    private final UserBookDTOMapper bookDTOMapper;
    private final UserGameDTOMapper gameDTOMapper;

    private final UserFriendDTOMapper friendDTOMapper;


    public ProfileDTO getUser(String nick) {
        return userRepository.findByNick(nick).map(profileMapper).orElseThrow(() -> new ProfileNotFoundException(nick));
    }

    public List<FriendDTO> getUserFriends(String nick) {
        return friendDTOMapper.applyAllFriends(userRepository.findByNick(nick).orElseThrow(() -> new ProfileNotFoundException(nick)));
    }

    public List<FriendDTO> getUsersFriendSuggestion(String nick) {
        return friendDTOMapper.applyFriendSuggestion(userRepository.findByNick(nick).orElseThrow(() -> new ProfileNotFoundException(nick)));
    }

    public List<LightFriendDTO> getUserUnapprovedFriends(String nick) {
        return friendDTOMapper.applyLightFriends(userRepository.findByNick(nick).orElseThrow(() -> new ProfileNotFoundException(nick)));
    }

    public UserFullFilmDTO getUserFilmReview(String nick, String filmId) {
        if (Constants.isNumberCheck(filmId)) {
            Optional<ProfileFilm> userFilm = userFilmRepository.findByProfileEntityNickAndProductId(nick, Long.parseLong(filmId));
            if (userFilm.isPresent())
                return filmMapper.apply(userFilm.get(), filmRepository.findById(userFilm.get().getProductId()).get());
        }
        throw new ProductAndProfileException(FILM_NAME, nick, filmId);
    }

    public UserFullGameDTO getUserGameReview(String nick, String gameId) {
        if (Constants.isNumberCheck(gameId)) {
            Optional<ProfileGame> userGame = userGameRepository.findByProfileEntityNickAndProductId(nick, Long.parseLong(gameId));
            if (userGame.isPresent())
                return gameMapper.apply(userGame.get(), gameRepository.findById(userGame.get().getProductId()).get());
        }
        throw new ProductAndProfileException(GAME_NAME, nick, gameId);
    }

    public UserFullBookDTO getUserBookReview(String nick, String bookId) {
        if (Constants.isNumberCheck(bookId)) {
            Optional<ProfileBook> userBook = userBookRepository.findByProfileEntityNickAndProductId(nick, Long.parseLong(bookId));
            if (userBook.isPresent())
                return bookMapper.apply(userBook.get(), bookRepository.findById(userBook.get().getProductId()).get());
        }
        throw new ProductAndProfileException(BOOK_NAME, nick, bookId);
    }

    public List<ProductReviewDTO> getProductReviews(String id) {
        if(!Constants.isNumberCheck(id))
            throw new TypeErrorException();
        return reviewRepository.findAllByProduct(Long.parseLong(id)).stream().map(reviewDTOMapper).toList();
    }


    public List<UserFilmDTO> getUserFilms(String nick, long film) {
        return userFilmRepository.findAllByProfileEntityNickAndProductEntityTypeId(nick,film).stream().map(filmDTOMapper).toList();
    }

    public List<UserBookDTO> getUserBooks(String nick, long book) {
        return userBookRepository.findAllByProfileEntityNickAndProductEntityTypeId(nick,book).stream().map(bookDTOMapper).toList();
    }

    public List<UserGameDTO> getUserGames(String nick, long game) {
        return userGameRepository.findAllByProfileEntityNickAndProductEntityTypeId(nick,game).stream().map(gameDTOMapper).toList();
    }
}
