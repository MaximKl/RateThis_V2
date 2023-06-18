package com.ratethis.reviewservice.controller;

import com.ratethis.reviewservice.authentication.Authenticate;
import com.ratethis.reviewservice.dto.ReactionDTO;
import com.ratethis.reviewservice.dto.MessageDTO;
import com.ratethis.reviewservice.dto.ReportDTO;
import com.ratethis.reviewservice.dto.ReviewDTO;
import com.ratethis.reviewservice.model.Report;
import com.ratethis.reviewservice.model.Review;
import com.ratethis.reviewservice.model.UserRole;
import com.ratethis.reviewservice.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/RateThis-review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/thisUserReview/{productId}")
    public ResponseEntity<ReviewDTO> thisUserReview(@PathVariable("productId") String productId, @CookieValue(name = "token") String token) {
        return new ResponseEntity<>(reviewService.getThisUserReview(productId, token), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/thisUserReview")
    public ResponseEntity<ReviewDTO> saveReview(@RequestBody @Valid Review review, @CookieValue(name = "token") String token) {
        return new ResponseEntity<>(reviewService.saveReview(review, token), HttpStatusCode.valueOf(201));
    }

    @PostMapping("/thisUserReview/{productId}")
    public ResponseEntity<ReviewDTO> updateReview(@RequestBody @Valid MessageDTO message, @PathVariable("productId") String productId,
                                                  @CookieValue(name = "token") String token) {
        return new ResponseEntity<>(reviewService.updateReview(message.getMessage(), productId, token), HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/thisUserReview/{productId}")
    public ResponseEntity deleteReview(@PathVariable("productId") String productId, @CookieValue(name = "token") String token) {
        reviewService.removeReview(productId, token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/thisUserReactions")
    public ResponseEntity<List<ReactionDTO>> allReactionsOfUser(@CookieValue(name = "token") String token) {
        return new ResponseEntity<>(reviewService.findAllUserReactions(token), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/addReaction/{reviewId}/{reaction}")
    public ResponseEntity addUserReaction(@PathVariable(value = "reviewId") String reviewId,@PathVariable(value = "reaction") String reaction,
                                          @CookieValue(name = "token") String token) {
        reviewService.addReaction(reviewId, reaction, token);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }

    @PostMapping("/addReport/{reviewId}")
    public ResponseEntity addUserReport(@RequestBody @Valid Report report, @PathVariable(value = "reviewId") String reviewId,
                                        @CookieValue(name = "token") String token) {
        reviewService.addReport(reviewId, report.getReportBody(), token);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }

    @GetMapping("/getReports")
    public List<ReportDTO> getAllUserReports(@CookieValue(name = "token") String token){
        return reviewService.getAllUserReports(token);
    }

    @Authenticate(role = UserRole.ADMIN)
    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity deleteReviewByAdmin(@PathVariable("reviewId") String reviewId, @CookieValue(name = "token") String token) {
        reviewService.removeReviewByAdmin(reviewId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
