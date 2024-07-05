package com.urbanbazaar.Service;

import com.urbanbazaar.DTO.ReviewsDto;

public interface ReviewService {
    ReviewsDto addReview(String productId, ReviewsDto reviewDto);
    ReviewsDto updateReview(String productId, ReviewsDto reviewDto);
}
