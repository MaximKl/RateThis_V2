package com.ratethis.reviewservice.service;


import com.ratethis.reviewservice.dto.MessageDTO;
import com.ratethis.reviewservice.dto.ReactionDTO;
import com.ratethis.reviewservice.dto.ReportDTO;
import com.ratethis.reviewservice.dto.ReviewDTO;
import com.ratethis.reviewservice.dto.mapper.ReactionDTOMapper;
import com.ratethis.reviewservice.dto.mapper.ReportDTOMapper;
import com.ratethis.reviewservice.dto.mapper.ReviewDTOMapper;
import com.ratethis.reviewservice.exception.IdentificationException;
import com.ratethis.reviewservice.exception.ReviewNotFoundException;
import com.ratethis.reviewservice.exception.TypeErrorException;
import com.ratethis.reviewservice.model.Reaction;
import com.ratethis.reviewservice.model.Report;
import com.ratethis.reviewservice.model.Review;
import com.ratethis.reviewservice.repository.ReactionRepository;
import com.ratethis.reviewservice.repository.ReportRepository;
import com.ratethis.reviewservice.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.ratethis.reviewservice.constants.Constants.isNumberCheck;
import static com.ratethis.reviewservice.constants.Constants.userIdIdentification;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReactionRepository reactionRepository;

    private final ReportRepository reportRepository;

    private final ReviewDTOMapper reviewDTOMapper;

    private final ReactionDTOMapper reactionDTOMapper;

    private final ReportDTOMapper reportDTOMapper;

    public ReviewDTO getThisUserReview(String productId, String token) {
        if (!isNumberCheck(productId))
            throw new TypeErrorException();

        return reviewDTOMapper.apply(reviewRepository.findReviewByUserIdAndProductId(userIdIdentification(token), Long.parseLong(productId))
                .orElseThrow(() -> new ReviewNotFoundException(productId)));
    }

    public ReviewDTO saveReview(Review review, String token) {
        if (review.getUserId() != userIdIdentification(token))
            throw new IdentificationException();
        if(reviewRepository.isProductExist(review.getProductId())==0)
            throw new TypeErrorException();
        review.setId(0);
        review.setLike(0);
        review.setDislike(0);
        review.setTime(Timestamp.valueOf(LocalDateTime.now()));
        review.setEdit(false);
        review.setEditTime(Timestamp.valueOf(LocalDateTime.now()));
        return reviewDTOMapper.apply(reviewRepository.save(review));
    }

    public ReviewDTO updateReview(String reviewBody, String productId, String token) {
        if (!isNumberCheck(productId))
            throw new TypeErrorException();
        Review review = reviewRepository.findReviewByUserIdAndProductId(userIdIdentification(token), Long.parseLong(productId))
                .orElseThrow(() -> new ReviewNotFoundException(productId));
        review.setBody(reviewBody);
        review.setEdit(true);
        review.setEditTime(Timestamp.valueOf(LocalDateTime.now()));
        return reviewDTOMapper.apply(reviewRepository.save(review));
    }

    @Transactional
    public void removeReview(String productId, String token) {
        if (!isNumberCheck(productId))
            throw new TypeErrorException();
        reviewRepository.deleteReviewByUserIdAndProductId(userIdIdentification(token), Long.parseLong(productId));
    }

    public List<ReactionDTO> findAllUserReactions(String token) {
        return reactionRepository.findAllByUserId(userIdIdentification(token)).stream().map(reactionDTOMapper).toList();
    }

    @Transactional
    public void addReaction(String reviewId, String reaction, String token) {
        if (!isNumberCheck(reviewId))
            throw new TypeErrorException();
        if (!isNumberCheck(reaction))
            throw new TypeErrorException();
        long reviewIdNum = Long.parseLong(reviewId);
        long userId = userIdIdentification(token);
        if (Integer.parseInt(reaction) == 1) {
            Reaction existingReaction = reactionRepository.findReactionByUserIdAndReviewId(userId, reviewIdNum);
            if (existingReaction == null) {
                reactionRepository.save(new Reaction(userId, reviewIdNum, true, false));
            } else if (existingReaction.isDislike()) {
                reactionRepository.save(new Reaction(userId, reviewIdNum, true, false));
            } else if (existingReaction.isLike()) {
                reactionRepository.deleteReactionByUserIdAndReviewId(userId, reviewIdNum);
            }
        } else if (Integer.parseInt(reaction) == 0) {
            Reaction existingReaction = reactionRepository.findReactionByUserIdAndReviewId(userId, reviewIdNum);
            if (existingReaction == null) {
                reactionRepository.save(new Reaction(userId, reviewIdNum, false, true));
            } else if (existingReaction.isLike()) {
                reactionRepository.save(new Reaction(userId, reviewIdNum, false, true));
            } else if (existingReaction.isDislike()) {
                reactionRepository.deleteReactionByUserIdAndReviewId(userId, reviewIdNum);
            }
        }
    }

    public void addReport(String reviewId, String message, String token) {
        if (!isNumberCheck(reviewId))
            throw new TypeErrorException();
        reportRepository.save(new Report(Long.parseLong(reviewId),userIdIdentification(token),message,Timestamp.valueOf(LocalDateTime.now())));
    }

    public List<ReportDTO> getAllUserReports(String token){
        return reportRepository.findAllByProfileId(userIdIdentification(token)).stream().map(reportDTOMapper).toList();
    }

    public void removeReviewByAdmin(String reviewId) {
        if (!isNumberCheck(reviewId))
            throw new TypeErrorException();
        reviewRepository.deleteById(Long.parseLong(reviewId));
    }
}
