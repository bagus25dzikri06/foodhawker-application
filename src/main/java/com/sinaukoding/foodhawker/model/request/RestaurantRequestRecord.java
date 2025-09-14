package com.sinaukoding.foodhawker.model.request;

import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RestaurantRequestRecord(
        String restaurantId,
        @NotBlank(message = "Name may not be empty") String name,
        @NotBlank(message = "Description may not be empty") String description,
        @NotBlank(message = "Cuisine type may not be empty") String cuisineType,
        @NotBlank(message = "Opening hours may not be empty") String openingHours,
        @NotNull(message = "Status may not be empty") Status status,
        @NotEmpty(message = "Image may not be empty")Set<String> listImage
) {
}
