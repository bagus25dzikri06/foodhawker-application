package com.sinaukoding.foodhawker.service.transaction;

import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.OrderFilterRecord;
import com.sinaukoding.foodhawker.model.request.OrderRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    void add(OrderRequestRecord request);
    void edit(OrderRequestRecord request);
    Page<SimpleMap> findAll(OrderFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}