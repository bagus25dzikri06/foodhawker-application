package com.sinaukoding.foodhawker.service.master;

import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.RestaurantFilterRecord;
import com.sinaukoding.foodhawker.model.request.RestaurantRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantService {
    void add(RestaurantRequestRecord request);
    void edit(RestaurantRequestRecord request);
    Page<SimpleMap> findAll(RestaurantFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}