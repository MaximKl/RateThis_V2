package com.ratethis.productservice.dto.mapper;

import com.ratethis.productservice.dto.ProfileBookDTO;
import com.ratethis.productservice.model.product.profile.ProfileBook;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfileBookDTOMapper implements Function<ProfileBook, ProfileBookDTO> {
    @Override
    public ProfileBookDTO apply(ProfileBook profileBook) {
        return new ProfileBookDTO(
                profileBook.getStoryMark(),
                profileBook.getArtMark(),
                profileBook.getInfoMark(),
                profileBook.getRating(),
                profileBook.getRateDate());
    }
}
