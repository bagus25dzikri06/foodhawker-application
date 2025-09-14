package com.sinaukoding.foodhawker.service.master;

import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.IngredientsItemFilterRecord;
import com.sinaukoding.foodhawker.model.request.IngredientsItemRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredientsItemService {
    void add(IngredientsItemRequestRecord request);
    void edit(IngredientsItemRequestRecord request);
    Page<SimpleMap> findAll(IngredientsItemFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}