package com.ratethis.productservice.service;


import com.ratethis.productservice.dto.ProfileBookDTO;
import com.ratethis.productservice.dto.mapper.ProfileBookDTOMapper;
import com.ratethis.productservice.exception.IdentificationException;
import com.ratethis.productservice.exception.ProductOrUserNotFoundException;
import com.ratethis.productservice.exception.TypeErrorException;
import com.ratethis.productservice.model.product.profile.ProfileBook;
import com.ratethis.productservice.repository.product.ProfileBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.ratethis.productservice.constants.Constants.*;

@Service
@AllArgsConstructor
public class BookService {

    private final ProfileBookRepository profileBookRepository;

    private final ProfileBookDTOMapper profileBookDTOMapper;

    public ProfileBookDTO saveEstimates(ProfileBook userEstimates, String token) {
        if (userIdIdentification(token) != userEstimates.getProfileId())
            throw new IdentificationException();
        userEstimates.setRateDate(Timestamp.valueOf(LocalDateTime.now()));
        return profileBookDTOMapper.apply(profileBookRepository.save(userEstimates));
    }

    public ProfileBookDTO getPersonalEstimates(String bookId, String token) {
        if (!isNumberCheck(bookId)) {
            throw new TypeErrorException();
        }
        long userId = userIdIdentification(token);
        return profileBookDTOMapper.apply(profileBookRepository.findProfileBookByProfileIdAndProductId(userId, Long.parseLong(bookId))
                .orElseThrow(() -> new ProductOrUserNotFoundException(String.valueOf(userId), bookId)));
    }

    @Transactional
    public void removePersonalEstimates(String bookId, String token) {
        if (!isNumberCheck(bookId)) {
            throw new TypeErrorException();
        }
        long userId = userIdIdentification(token);
        profileBookRepository.deleteProfileBookByProfileIdAndProductId(userId, Long.parseLong(bookId));
    }
}
