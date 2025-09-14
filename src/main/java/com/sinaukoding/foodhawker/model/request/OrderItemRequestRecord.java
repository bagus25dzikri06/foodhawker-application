package com.sinaukoding.foodhawker.model.request;

import com.sinaukoding.foodhawker.entity.master.Food;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequestRecord(
        String id,
        @NotBlank(message = "Food may not be empty") Food foodId,
        @NotNull(message = "Quantity may not be empty") Integer quantity,
        @NotNull(message = "Total price may not be empty") Long totalPrice,
        @NotNull(message = "Status may not be empty") Status status
) {
}