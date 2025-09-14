package com.sinaukoding.foodhawker.mapper.master;

import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.entity.master.RestaurantImage;
import com.sinaukoding.foodhawker.model.request.RestaurantRequestRecord;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RestaurantMapper {
    public Restaurant requestToEntity(RestaurantRequestRecord request) {
        Restaurant restaurant = Restaurant.builder()
                .name(request.name().toUpperCase())
                .description(request.description().toLowerCase())
                .cuisineType(request.cuisineType().toLowerCase())
                .openingHours(request.openingHours())
                .status(request.status())
                .build();

        restaurant.setListImage(request.listImage().stream()
                .map(path -> RestaurantImage.builder()
                        .path(path)
                        .restaurant(restaurant)
                        .build())
                .collect(Collectors.toSet()));

        return restaurant;
    }
}