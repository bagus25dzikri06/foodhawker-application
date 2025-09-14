package com.sinaukoding.foodhawker.service.transaction;

import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.OrderItemFilterRecord;
import com.sinaukoding.foodhawker.model.request.OrderItemRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    void add(OrderItemRequestRecord request);
    void edit(OrderItemRequestRecord request);
    Page<SimpleMap> findAll(OrderItemFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}