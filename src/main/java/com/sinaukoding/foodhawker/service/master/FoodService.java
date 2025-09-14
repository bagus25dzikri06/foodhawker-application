package com.sinaukoding.foodhawker.service.master;

import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.FoodFilterRecord;
import com.sinaukoding.foodhawker.model.request.FoodRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodService {
    void add(FoodRequestRecord request);
    void edit(FoodRequestRecord request);
    Page<SimpleMap> findAll(FoodFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}