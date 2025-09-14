package com.sinaukoding.foodhawker.model.request;

import com.sinaukoding.foodhawker.entity.master.IngredientsCategory;
import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IngredientsItemRequestRecord(
        String id,
        @NotBlank(message = "Name may not be empty") String name,
        @NotBlank(message = "Ingredients category ID may not be empty") IngredientsCategory ingredientsCategoryId,
        @NotBlank(message = "Restaurant ID may not be empty") Restaurant restaurantId,
        @NotNull(message = "Stoke may not be empty") boolean isInStoke,
        @NotNull(message = "Status may not be empty") Status status
) {
}