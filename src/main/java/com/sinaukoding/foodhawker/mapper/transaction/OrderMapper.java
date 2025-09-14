package com.sinaukoding.foodhawker.mapper.transaction;

import com.sinaukoding.foodhawker.entity.transaction.Order;
import com.sinaukoding.foodhawker.model.request.OrderRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order requestToEntity(OrderRequestRecord request) {
        return Order.builder()
                .restaurant(request.restaurantId())
                .totalAmount(request.totalAmount())
                .orderStatus(request.orderStatus())
                .orderItems(request.orderItems())
                .status(request.status())
                .build();
    }
}