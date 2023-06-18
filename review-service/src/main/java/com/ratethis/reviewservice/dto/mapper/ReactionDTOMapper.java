package com.ratethis.reviewservice.dto.mapper;

import com.ratethis.reviewservice.dto.ReactionDTO;
import com.ratethis.reviewservice.model.Reaction;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReactionDTOMapper implements Function<Reaction, ReactionDTO> {
    @Override
    public ReactionDTO apply(Reaction reaction) {
        return new ReactionDTO(reaction.getReviewId(),
                reaction.isLike(),
                reaction.isDislike());
    }
}
