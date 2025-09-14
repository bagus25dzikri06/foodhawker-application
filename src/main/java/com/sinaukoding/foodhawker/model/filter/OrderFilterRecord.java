package com.sinaukoding.foodhawker.model.filter;

import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.entity.transaction.OrderItem;
import com.sinaukoding.foodhawker.model.enums.OrderStatus;
import com.sinaukoding.foodhawker.model.enums.Status;

import java.util.List;

public record OrderFilterRecord(
        Restaurant restaurantId,
        Long totalAmount,
        OrderStatus orderStatus,
        List<OrderItem> orderItems,
        Status status
) {
}
