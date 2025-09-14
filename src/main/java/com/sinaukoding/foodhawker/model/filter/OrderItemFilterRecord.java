package com.sinaukoding.foodhawker.model.filter;

import com.sinaukoding.foodhawker.entity.master.Food;
import com.sinaukoding.foodhawker.model.enums.Status;

public record OrderItemFilterRecord(
        Food foodId,
        Integer quantity,
        Long totalPrice,
        Status status
) {
}
