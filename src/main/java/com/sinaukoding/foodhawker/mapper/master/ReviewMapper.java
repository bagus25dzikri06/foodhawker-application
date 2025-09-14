package com.sinaukoding.foodhawker.mapper.master;

import com.sinaukoding.foodhawker.entity.master.Review;
import com.sinaukoding.foodhawker.model.request.ReviewRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review requestToEntity(ReviewRequestRecord request) {
        return Review.builder()
                .restaurant(request.restaurantId())
                .message(request.message())
                .rating(request.rating())
                .status(request.status())
                .build();
    }
}