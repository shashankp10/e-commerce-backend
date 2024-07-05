package com.urbanbazaar.Service.Impl;

import com.urbanbazaar.DTO.ReviewsDto;
import com.urbanbazaar.Entity.Product;
import com.urbanbazaar.Entity.Reviews;
import com.urbanbazaar.Repo.mongo.ProductRepo;
import com.urbanbazaar.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public ReviewsDto addReview(String productId, ReviewsDto reviewDto) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (!optionalProduct.isPresent()) {
            return null;
        }

        Product product = optionalProduct.get();
        Reviews review = convertDtoToEntity(reviewDto);

        if (review.getDate() == null) {
            review.setDate(getCurrentTimeWithTimeZone());
        }

        if (product.getReviews() == null) {
            product.setReviews(new ArrayList<>());
        }

        product.getReviews().add(review);
        productRepo.save(product);
        return reviewDto;
    }

    @Override
    public ReviewsDto updateReview(String productId, ReviewsDto reviewDto) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (!optionalProduct.isPresent()) {
            return null;
        }

        Product product = optionalProduct.get();
        List<Reviews> reviewsList = product.getReviews();

        if (reviewsList == null) {
            return null;
        }
        reviewsList.add(convertDtoToEntity(reviewDto));

        productRepo.save(product);
        return reviewDto;
    }

    private Reviews convertDtoToEntity(ReviewsDto dto) {
        Reviews review = new Reviews();
        review.setId(dto.getId());
        review.setName(dto.getName());
        review.setRating(dto.getRating());
        review.setFeedback(dto.getFeedback());
        review.setDate(dto.getDate());
        return review;
    }

    private Date getCurrentTimeWithTimeZone() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
        String formattedDate = sdf.format(new Date());
        try {
            return sdf.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
