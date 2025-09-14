package com.sinaukoding.foodhawker.model.request;

import com.sinaukoding.foodhawker.entity.master.IngredientsItem;
import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record IngredientsCategoryRequestRecord(
        String ingredientsCategoryId,
        @NotBlank(message = "Name may not be empty") String name,
        @NotBlank(message = "Restaurant ID may not be empty") Restaurant restaurantId,
        @NotEmpty(message = "Ingredients may not be empty") List<IngredientsItem> ingredients,
        @NotNull(message = "Status may not be empty") Status status
) {
}