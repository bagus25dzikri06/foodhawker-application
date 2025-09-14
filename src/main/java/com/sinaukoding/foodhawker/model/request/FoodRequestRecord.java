package com.sinaukoding.foodhawker.model.request;

import com.sinaukoding.foodhawker.entity.master.Category;
import com.sinaukoding.foodhawker.entity.master.IngredientsItem;
import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public record FoodRequestRecord(
        String foodId,
        @NotBlank(message = "Name may not be empty") String name,
        @NotBlank(message = "Description may not be empty") String description,
        @NotBlank(message = "Price may not be empty") Double price,
        @NotBlank(message = "Category ID may not be empty") Category categoryId,
        @NotNull(message = "Availability may not be empty") boolean available,
        @NotBlank(message = "Restaurant ID may not be empty") Restaurant restaurantId,
        @NotNull(message = "Vegetarian may not be empty") boolean vegetarian,
        @NotNull(message = "Season may not be empty") boolean seasonal,
        @NotEmpty(message = "Ingredients may not be empty") List<IngredientsItem> ingredients,
        @NotNull(message = "Status may not be empty") Status status,
        @NotEmpty(message = "Image may not be empty") Set<String> listImage
) {
}
