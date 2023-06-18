package com.ratethis.reviewservice.dto;

public record ReactionDTO(long reviewId,
                          boolean isLike,
                          boolean isDislike) {

}
