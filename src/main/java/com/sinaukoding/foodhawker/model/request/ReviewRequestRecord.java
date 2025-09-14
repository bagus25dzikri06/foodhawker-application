package com.sinaukoding.foodhawker.model.request;

import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewRequestRecord(
        String id,
        @NotBlank(message = "Restaurant ID may not be empty") Restaurant restaurantId,
        @NotBlank(message = "Message may not be empty") String message,
        @NotNull(message = "Rating may not be empty") Double rating,
        @NotNull(message = "Status may not be empty") Status status
) {
}