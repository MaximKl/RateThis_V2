package com.ratethis.publicservice.service;

import com.ratethis.publicservice.dto.mapper.productmap.ProductDeveloperDTOMapper;
import com.ratethis.publicservice.dto.mapper.productmap.ProductReviewDTOMapper;
import com.ratethis.publicservice.dto.mapper.usermap.*;
import com.ratethis.publicservice.exception.ProductAndProfileException;
import com.ratethis.publicservice.exception.ProfileNotFoundException;
import com.ratethis.publicservice.model.*;
import com.ratethis.publicservice.repository.*;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    @Mock
    private ProductFilmRepository filmRepository;
    @Mock
    private ProductGameRepository gameRepository;
    @Mock
    private ProductBookRepository bookRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProfileGameRepository userGameRepository;
    @Mock
    private ProfileFilmRepository userFilmRepository;
    @Mock
    private ProfileBookRepository userBookRepository;

    private UserService underTest;

    @BeforeEach()
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ProductDeveloperDTOMapper developerDTOMapper = new ProductDeveloperDTOMapper();
        UserFriendDTOMapper friendMapper = new UserFriendDTOMapper();
        UserBookDTOMapper bookDTOMapper = new UserBookDTOMapper(developerDTOMapper);
        UserGameDTOMapper gameDTOMapper = new UserGameDTOMapper(developerDTOMapper);
        UserFilmDTOMapper filmDTOMapper = new UserFilmDTOMapper(developerDTOMapper);
        ProfileDTOMapper profileMapper = new ProfileDTOMapper(bookDTOMapper, gameDTOMapper, filmDTOMapper, friendMapper);
        ProductReviewDTOMapper reviewMapper = new ProductReviewDTOMapper();
        UserFullGameDTOMapper gameMapper = new UserFullGameDTOMapper(developerDTOMapper, reviewMapper);
        UserFullFilmDTOMapper filmMapper = new UserFullFilmDTOMapper(developerDTOMapper, reviewMapper);
        UserFullBookDTOMapper bookMapper = new UserFullBookDTOMapper(developerDTOMapper, reviewMapper);
        underTest = new UserService(profileMapper, userRepository, userGameRepository, userFilmRepository, userBookRepository, filmRepository, gameRepository, bookRepository, gameMapper, filmMapper, bookMapper);

        Product film = new Product(1, "film", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        Product game = new Product(2, "game", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        Product book = new Product(3, "book", 0, null, "опис", null, "image", "18+", 0, 0, new ArrayList<>(), new ArrayList<>(), new ProductType(1, "type"), new ArrayList<>(), new HashMap<>());
        UserProfile user = new UserProfile(1, "oleg", "1234", "email@gmail.com", "опис", null, null, 0, "image", new ArrayList<>(), new UserRole(1, "role"), new UserColor(1, "color"), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());


        Mockito.when(filmRepository.findById(1L)).thenReturn(Optional.of(new ProductFilm(1, film, 0, 0, 0, 0, 0)));
        Mockito.when(gameRepository.findById(2L)).thenReturn(Optional.of(new ProductGame(2, game, 0, 0, 0, 0, 0, new ArrayList<>())));
        Mockito.when(bookRepository.findById(3L)).thenReturn(Optional.of(new ProductBook(3, book, 0, 0, 0, 0)));


        Mockito.when(userRepository.findByNick("oleg")).thenReturn(Optional.of(user));


        Mockito.when(userFilmRepository.findByProfileEntityNickAndProductId("oleg", 1)).thenReturn(Optional.of(new ProfileFilm(1,1,user,film , 0, 0, 0, 0, 0,null)));
        Mockito.when(userGameRepository.findByProfileEntityNickAndProductId("oleg", 2)).thenReturn(Optional.of(new ProfileGame(1,2,user,game, 0, 0, 0, 0,0,0,null)));
        Mockito.when(userBookRepository.findByProfileEntityNickAndProductId("oleg", 3)).thenReturn(Optional.of(new ProfileBook(1,3,user,book, 0, 0, 0, 0,null)));

    }

    @Test
    @DisplayName("Should retrieve user")
    void canGetUser() {
        Assertions.assertNotNull(underTest.getUser("oleg"));
    }

    @Test()
    @DisplayName("Cannot retrieve user")
    void cannotGetUser() {
        assertThrows(ProfileNotFoundException.class, () -> underTest.getUser("ASVDFsdf34"));
    }

    @Test
    @DisplayName("Should retrieve specific user book")
    void canGetSpecificBook() {
        Assertions.assertNotNull(underTest.getUserBookReview("oleg","3"));
    }

    @Test()
    @DisplayName("Cannot retrieve specific user book")
    void cannotGetSpecificBook() {
        assertThrows(ProductAndProfileException.class, () -> underTest.getUserBookReview("oleg","dsfrt454fg"));
    }

    @Test
    @DisplayName("Should retrieve specific user game")
    void canGetSpecificGame() {
        Assertions.assertNotNull(underTest.getUserGameReview("oleg","2"));
    }

    @Test
    @DisplayName("Cannot retrieve specific user game")
    void cannotGetSpecificGame() {
        assertThrows(ProductAndProfileException.class, () ->underTest.getUserGameReview("olefdg5g","sdf564"));
    }

    @Test
    @DisplayName("Should retrieve specific user film")
    void canGetSpecificFilm() {
        Assertions.assertNotNull(underTest.getUserFilmReview("oleg","1"));
    }
    @Test
    @DisplayName("Cannot retrieve specific user film")
    void cannotGetSpecificFilm() {
        assertThrows(ProductAndProfileException.class, () ->underTest.getUserFilmReview("oleg","5467"));
    }
}
