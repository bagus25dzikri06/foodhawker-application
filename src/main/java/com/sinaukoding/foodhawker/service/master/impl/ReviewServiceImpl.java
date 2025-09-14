package com.sinaukoding.foodhawker.service.master.impl;

import com.sinaukoding.foodhawker.builder.CustomBuilder;
import com.sinaukoding.foodhawker.entity.master.Review;
import com.sinaukoding.foodhawker.mapper.master.ReviewMapper;
import com.sinaukoding.foodhawker.model.app.AppPage;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.ReviewFilterRecord;
import com.sinaukoding.foodhawker.model.request.ReviewRequestRecord;
import com.sinaukoding.foodhawker.repository.master.ReviewRepository;
import com.sinaukoding.foodhawker.service.app.ValidatorService;
import com.sinaukoding.foodhawker.service.master.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private ValidatorService validatorService;
    private ReviewMapper reviewMapper;

    @Override
    public void add(ReviewRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data review");
            log.debug("Request data review: {}", request);

            // validator mandatory
            validatorService.validator(request);

            var review = reviewMapper.requestToEntity(request);
            reviewRepository.save(review);

            log.info("Review {} berhasil ditambahkan", request.restaurantId());
            log.trace("Tambah data review berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data review gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(ReviewRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var reviewExisting = reviewRepository.findById(request.id()).orElseThrow(
                () -> new RuntimeException("Review not found")
        );
        var review = reviewMapper.requestToEntity(request);
        review.setId(reviewExisting.getId());
        reviewRepository.save(review);
    }

    @Override
    public Page<SimpleMap> findAll(ReviewFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Review> builder = new CustomBuilder<>();

        Page<Review> listReview = reviewRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listReview.stream().map(review -> {
            SimpleMap data = new SimpleMap();
            data.put("restaurant", review.getRestaurant());
            data.put("message", review.getMessage());
            data.put("rating", review.getRating());
            data.put("status", review.getStatus());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listReview.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var review = reviewRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Review item not found")
        );

        SimpleMap data = new SimpleMap();
        data.put("restaurant", review.getRestaurant());
        data.put("message", review.getMessage());
        data.put("rating", review.getRating());
        data.put("status", review.getStatus());
        return data;
    }
}
