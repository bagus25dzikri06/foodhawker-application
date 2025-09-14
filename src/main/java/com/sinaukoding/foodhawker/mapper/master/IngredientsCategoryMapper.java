package com.sinaukoding.foodhawker.mapper.master;

import com.sinaukoding.foodhawker.entity.master.IngredientsCategory;
import com.sinaukoding.foodhawker.model.request.IngredientsCategoryRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class IngredientsCategoryMapper {
    public IngredientsCategory requestToEntity(
            IngredientsCategoryRequestRecord request
    ) {
        return IngredientsCategory.builder()
                .name(request.name().toUpperCase())
                .restaurant(request.restaurantId())
                .ingredients(request.ingredients())
                .status(request.status())
                .build();
    }
}