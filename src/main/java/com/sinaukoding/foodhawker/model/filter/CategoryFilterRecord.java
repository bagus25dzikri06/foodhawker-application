package com.sinaukoding.foodhawker.model.filter;

import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;

public record CategoryFilterRecord(
        String name,
        Restaurant restaurantId,
        Status status
) {
}
