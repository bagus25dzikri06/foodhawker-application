package com.sinaukoding.foodhawker.service.master;

import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.IngredientsCategoryFilterRecord;
import com.sinaukoding.foodhawker.model.request.IngredientsCategoryRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredientsCategoryService {
    void add(IngredientsCategoryRequestRecord request);
    void edit(IngredientsCategoryRequestRecord request);
    Page<SimpleMap> findAll(IngredientsCategoryFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}