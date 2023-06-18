package com.ratethis.productservice.service;

import com.ratethis.productservice.dto.ProfileFilmDTO;
import com.ratethis.productservice.dto.mapper.ProfileFilmDTOMapper;
import com.ratethis.productservice.exception.IdentificationException;
import com.ratethis.productservice.exception.ProductOrUserNotFoundException;
import com.ratethis.productservice.exception.TypeErrorException;
import com.ratethis.productservice.model.product.profile.ProfileFilm;
import com.ratethis.productservice.repository.product.ProfileFilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.ratethis.productservice.constants.Constants.*;

@Service
@AllArgsConstructor
public class FilmService {

    private final ProfileFilmRepository profileFilmRepository;

    private final ProfileFilmDTOMapper profileFilmDTOMapper;

    public ProfileFilmDTO saveEstimates(ProfileFilm userEstimates, String token) {
        if (userIdIdentification(token) != userEstimates.getProfileId())
            throw new IdentificationException();
        userEstimates.setRateDate(Timestamp.valueOf(LocalDateTime.now()));
        return profileFilmDTOMapper.apply(profileFilmRepository.save(userEstimates));
    }

    public ProfileFilmDTO getPersonalEstimates(String filmId, String token) {
        if (!isNumberCheck(filmId))
            throw new TypeErrorException();

        long userId = userIdIdentification(token);
        return profileFilmDTOMapper.apply(profileFilmRepository.findProfileFilmByProfileIdAndProductId(userId, Long.parseLong(filmId))
                .orElseThrow(() -> new ProductOrUserNotFoundException(String.valueOf(userId), filmId)));
    }

    @Transactional
    public void removePersonalEstimates(String filmId, String token) {
        if (!isNumberCheck(filmId))
            throw new TypeErrorException();

        profileFilmRepository.deleteProfileFilmByProfileIdAndProductId(userIdIdentification(token), Long.parseLong(filmId));
    }
}
