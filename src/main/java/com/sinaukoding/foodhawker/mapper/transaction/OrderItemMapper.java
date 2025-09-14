package com.sinaukoding.foodhawker.mapper.transaction;

import com.sinaukoding.foodhawker.entity.transaction.OrderItem;
import com.sinaukoding.foodhawker.model.request.OrderItemRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItem requestToEntity(OrderItemRequestRecord request) {
        return OrderItem.builder()
                .food(request.foodId())
                .quantity(request.quantity())
                .totalPrice(request.totalPrice())
                .status(request.status())
                .build();
    }
}