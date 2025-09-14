package com.sinaukoding.foodhawker.model.filter;

import com.sinaukoding.foodhawker.entity.master.IngredientsItem;
import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;

import java.util.List;

public record IngredientsCategoryFilterRecord(
        String name,
        Restaurant restaurantId,
        List<IngredientsItem> ingredients,
        Status status
) {
}
