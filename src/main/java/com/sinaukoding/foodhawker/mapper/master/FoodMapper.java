package com.sinaukoding.foodhawker.mapper.master;

import com.sinaukoding.foodhawker.entity.master.Food;
import com.sinaukoding.foodhawker.entity.master.FoodImage;
import com.sinaukoding.foodhawker.model.request.FoodRequestRecord;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FoodMapper {
    public Food requestToEntity(FoodRequestRecord request) {
        Food food = Food.builder()
                .name(request.name().toUpperCase())
                .description(request.description().toLowerCase())
                .price(request.price())
                .category(request.categoryId())
                .available(request.available())
                .restaurant(request.restaurantId())
                .vegetarian(request.vegetarian())
                .seasonal(request.seasonal())
                .ingredients(request.ingredients())
                .status(request.status())
                .build();

        food.setListImage(request.listImage().stream()
                .map(path -> FoodImage.builder()
                        .path(path)
                        .food(food)
                        .build())
                .collect(Collectors.toSet()));

        return food;
    }
}