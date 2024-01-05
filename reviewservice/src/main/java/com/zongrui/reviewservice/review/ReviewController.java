package com.zongrui.reviewservice.review;

import com.zongrui.reviewservice.review.messaging.ReviewMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMessageProducer reviewMessageProducer;
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review, @RequestParam Long companyId){
        boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved){
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        }

        else
            return new ResponseEntity<>("Review Not Saved", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId
            ,@RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(reviewId,review);
        if(isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review updated failed", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if(isReviewDeleted)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review deleted failed", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId){
        List<Review>reviewList =  reviewService.getAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }

}
