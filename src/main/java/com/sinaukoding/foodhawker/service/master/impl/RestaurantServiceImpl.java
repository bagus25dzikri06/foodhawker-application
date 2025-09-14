package com.sinaukoding.foodhawker.service.master.impl;

import com.sinaukoding.foodhawker.builder.CustomBuilder;
import com.sinaukoding.foodhawker.entity.master.Restaurant;
import com.sinaukoding.foodhawker.entity.master.RestaurantImage;
import com.sinaukoding.foodhawker.mapper.master.RestaurantMapper;
import com.sinaukoding.foodhawker.model.app.AppPage;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.RestaurantFilterRecord;
import com.sinaukoding.foodhawker.model.request.RestaurantRequestRecord;
import com.sinaukoding.foodhawker.repository.master.RestaurantRepository;
import com.sinaukoding.foodhawker.service.app.ValidatorService;
import com.sinaukoding.foodhawker.service.master.RestaurantService;
import com.sinaukoding.foodhawker.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;
    private ValidatorService validatorService;
    private RestaurantMapper restaurantMapper;

    @Override
    public void add(RestaurantRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data restoran");
            log.debug("Request data restoran: {}", request);

            // validator mandatory
            validatorService.validator(request);

            var restaurant = restaurantMapper.requestToEntity(request);
            restaurantRepository.save(restaurant);

            log.info("Restoran {} berhasil ditambahkan", request.name());
            log.trace("Tambah data restoran berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data restoran gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(RestaurantRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var restaurantExisting = restaurantRepository.findById(request.restaurantId()).orElseThrow(
                () -> new RuntimeException("Restaurant not found")
        );
        var restaurant = restaurantMapper.requestToEntity(request);
        restaurant.setRestaurantId(restaurantExisting.getRestaurantId());
        restaurantRepository.save(restaurant);
    }

    @Override
    public Page<SimpleMap> findAll(RestaurantFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Restaurant> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankEqual("name", filterRequest.name(), builder);
        FilterUtil.builderConditionNotBlankEqual("cuisineType", filterRequest.cuisineType(), builder);
        FilterUtil.builderConditionNotBlankEqual("status", filterRequest.status().toString(), builder);

        Page<Restaurant> listRestaurant = restaurantRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listRestaurant.stream().map(restaurant -> {
            SimpleMap data = new SimpleMap();
            data.put("name", restaurant.getName());
            data.put("description", restaurant.getDescription());
            data.put("cuisineType", restaurant.getCuisineType());
            data.put("openingHours", restaurant.getOpeningHours());
            data.put("status", restaurant.getStatus());
            data.put("listImage", restaurant.getListImage().stream().map(RestaurantImage::getPath).collect(Collectors.toSet()));
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listRestaurant.getTotalElements());
    }

    @Override
    public SimpleMap findById(String restaurantId) {
        var restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant item not found")
        );

        SimpleMap data = new SimpleMap();
        data.put("name", restaurant.getName());
        data.put("description", restaurant.getDescription());
        data.put("cuisineType", restaurant.getCuisineType());
        data.put("openingHours", restaurant.getOpeningHours());
        data.put("status", restaurant.getStatus());
        data.put("listImage", restaurant.getListImage().stream().map(RestaurantImage::getPath).collect(Collectors.toSet()));
        return data;
    }
}