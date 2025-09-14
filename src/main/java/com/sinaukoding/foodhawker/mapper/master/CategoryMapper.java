package com.sinaukoding.foodhawker.mapper.master;

import com.sinaukoding.foodhawker.entity.master.Category;
import com.sinaukoding.foodhawker.model.request.CategoryRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category requestToEntity(CategoryRequestRecord request) {
        return Category.builder()
                .name(request.name().toUpperCase())
                .restaurant(request.restaurantId())
                .status(request.status())
                .build();
    }
}