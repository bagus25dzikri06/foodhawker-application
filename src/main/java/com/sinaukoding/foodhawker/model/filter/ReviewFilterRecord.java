package com.sinaukoding.foodhawker.model.filter;

import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;

public record ReviewFilterRecord(
        Restaurant restaurantId,
        String message,
        Double rating,
        Status status
) {
}
