package com.sinaukoding.foodhawker.service.master;

import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.CategoryFilterRecord;
import com.sinaukoding.foodhawker.model.request.CategoryRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    void add(CategoryRequestRecord request);
    void edit(CategoryRequestRecord request);
    Page<SimpleMap> findAll(CategoryFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}