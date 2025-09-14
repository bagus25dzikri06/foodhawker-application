package com.sinaukoding.foodhawker.model.filter;

import com.sinaukoding.foodhawker.entity.master.Category;
import com.sinaukoding.foodhawker.entity.master.IngredientsItem;
import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;

import java.util.List;
import java.util.Set;

public record FoodFilterRecord(
        String name,
        String description,
        Double topPrice,
        Double lowerPrice,
        Category categoryId,
        boolean available,
        Restaurant restaurantId,
        boolean vegetarian,
        boolean seasonal,
        List<IngredientsItem> ingredients,
        Status status,
        Set<String> listImage
) {
}
