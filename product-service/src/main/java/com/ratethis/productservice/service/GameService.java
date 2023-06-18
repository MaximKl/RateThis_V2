package com.ratethis.productservice.service;

import com.ratethis.productservice.dto.ProfileGameDTO;
import com.ratethis.productservice.dto.mapper.ProfileGameDTOMapper;
import com.ratethis.productservice.exception.IdentificationException;
import com.ratethis.productservice.exception.ProductOrUserNotFoundException;
import com.ratethis.productservice.exception.TypeErrorException;
import com.ratethis.productservice.model.product.profile.ProfileGame;
import com.ratethis.productservice.repository.product.ProfileGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.ratethis.productservice.constants.Constants.*;

@Service
@AllArgsConstructor
public class GameService {

    private final ProfileGameRepository profileGameRepository;

    private final ProfileGameDTOMapper profileGameDTOMapper;

    public ProfileGameDTO saveEstimates(ProfileGame userEstimates, String token) {
        if (userIdIdentification(token) != userEstimates.getProfileId())
            throw new IdentificationException();
        userEstimates.setRateDate(Timestamp.valueOf(LocalDateTime.now()));
        return profileGameDTOMapper.apply(profileGameRepository.save(userEstimates));
    }

    public ProfileGameDTO getPersonalEstimates(String gameId, String token) {
        if (!isNumberCheck(gameId))
            throw new TypeErrorException();

        long userId = userIdIdentification(token);
        return profileGameDTOMapper.apply(profileGameRepository.findProfileGameByProfileIdAndProductId(userId, Long.parseLong(gameId))
                .orElseThrow(() -> new ProductOrUserNotFoundException(String.valueOf(userId), gameId)));
    }

    @Transactional
    public void removePersonalEstimates(String gameId, String token) {
        if (!isNumberCheck(gameId))
            throw new TypeErrorException();
        profileGameRepository.deleteProfileGameByProfileIdAndProductId(userIdIdentification(token), Long.parseLong(gameId));
    }
}
