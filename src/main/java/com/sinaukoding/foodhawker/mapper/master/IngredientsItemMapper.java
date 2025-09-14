package com.sinaukoding.foodhawker.mapper.master;

import com.sinaukoding.foodhawker.entity.master.IngredientsItem;
import com.sinaukoding.foodhawker.model.request.IngredientsItemRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class IngredientsItemMapper {
    public IngredientsItem requestToEntity(IngredientsItemRequestRecord request) {
        return IngredientsItem.builder()
                .name(request.name().toUpperCase())
                .ingredientsCategory(request.ingredientsCategoryId())
                .restaurant(request.restaurantId())
                .isInStoke(request.isInStoke())
                .status(request.status())
                .build();
    }
}