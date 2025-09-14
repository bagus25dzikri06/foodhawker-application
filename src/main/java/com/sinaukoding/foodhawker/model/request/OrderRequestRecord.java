package com.sinaukoding.foodhawker.model.request;

import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.entity.transaction.OrderItem;
import com.sinaukoding.foodhawker.model.enums.OrderStatus;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestRecord(
        String id,
        @NotBlank(message = "Restaurant may not be empty") Restaurant restaurantId,
        @NotNull(message = "Total amount may not be empty") Long totalAmount,
        @NotNull(message = "Order status may not be empty") OrderStatus orderStatus,
        @NotEmpty(message = "Order items may not be empty") List<OrderItem> orderItems,
        @NotNull(message = "Status may not be empty") Status status
) {
}