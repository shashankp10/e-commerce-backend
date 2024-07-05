package com.urbanbazaar.Controller;

import com.urbanbazaar.DTO.ReviewsDto;
import com.urbanbazaar.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("urbanbazaar/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{productId}")
    public ResponseEntity<ReviewsDto> addReview(@PathVariable String productId, @RequestBody ReviewsDto reviewDto) {
        ReviewsDto addedReview = reviewService.addReview(productId, reviewDto);
        if (addedReview == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addedReview);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ReviewsDto> updateReview(@PathVariable String productId, @RequestBody ReviewsDto reviewDto) {
        ReviewsDto updatedReview = reviewService.updateReview(productId, reviewDto);
        if (updatedReview == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedReview);
    }
}
