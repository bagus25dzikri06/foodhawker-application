package com.sinaukoding.foodhawker.service.master;

import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.ReviewFilterRecord;
import com.sinaukoding.foodhawker.model.request.ReviewRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    void add(ReviewRequestRecord request);
    void edit(ReviewRequestRecord request);
    Page<SimpleMap> findAll(ReviewFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}