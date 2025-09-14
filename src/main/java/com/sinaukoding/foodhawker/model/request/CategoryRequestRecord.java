package com.sinaukoding.foodhawker.model.request;

import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestRecord(
        String categoryId,
        @NotBlank(message = "Name may not be empty") String name,
        @NotBlank(message = "Restaurant ID may not be empty") Restaurant restaurantId,
        @NotNull(message = "Status may not be empty") Status status
) {
}