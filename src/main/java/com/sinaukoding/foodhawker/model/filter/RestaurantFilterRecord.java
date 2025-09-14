package com.sinaukoding.foodhawker.model.filter;

import com.sinaukoding.foodhawker.model.enums.Status;

import java.util.Set;

public record RestaurantFilterRecord(
        String name,
        String description,
        String cuisineType,
        String openingHours,
        Status status,
        Set<String> listImage
) {
}
