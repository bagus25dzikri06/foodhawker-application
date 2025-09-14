package com.sinaukoding.foodhawker.model.filter;

import com.sinaukoding.foodhawker.entity.master.IngredientsCategory;
import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;

public record IngredientsItemFilterRecord(
        String name,
        IngredientsCategory ingredientsCategoryId,
        Restaurant restaurantId,
        boolean isInStoke,
        Status status
) {
}
