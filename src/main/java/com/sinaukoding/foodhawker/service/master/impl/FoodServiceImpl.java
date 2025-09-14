package com.sinaukoding.foodhawker.service.master.impl;

import com.sinaukoding.foodhawker.builder.CustomBuilder;
import com.sinaukoding.foodhawker.builder.CustomSpecification;
import com.sinaukoding.foodhawker.builder.MultipleCriteria;
import com.sinaukoding.foodhawker.builder.SearchCriteria;
import com.sinaukoding.foodhawker.entity.master.Food;
import com.sinaukoding.foodhawker.entity.master.FoodImage;
import com.sinaukoding.foodhawker.entity.master.IngredientsItem;
import com.sinaukoding.foodhawker.mapper.master.FoodMapper;
import com.sinaukoding.foodhawker.model.app.AppPage;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.FoodFilterRecord;
import com.sinaukoding.foodhawker.model.request.FoodRequestRecord;
import com.sinaukoding.foodhawker.repository.master.FoodRepository;
import com.sinaukoding.foodhawker.service.app.ValidatorService;
import com.sinaukoding.foodhawker.service.master.FoodService;
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
public class FoodServiceImpl implements FoodService {
    private FoodRepository foodRepository;
    private ValidatorService validatorService;
    private FoodMapper foodMapper;

    @Override
    public void add(FoodRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data makanan");
            log.debug("Request data makanan: {}", request);

            // validator mandatory
            validatorService.validator(request);

            var food = foodMapper.requestToEntity(request);
            foodRepository.save(food);

            log.info("Kategori {} berhasil ditambahkan", request.name());
            log.trace("Tambah data makanan berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data makanan gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(FoodRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var foodExisting = foodRepository.findById(request.foodId()).orElseThrow(
                () -> new RuntimeException("Food not found")
        );
        var food = foodMapper.requestToEntity(request);
        food.setFoodId(foodExisting.getFoodId());
        foodRepository.save(food);
    }

    @Override
    public Page<SimpleMap> findAll(FoodFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Food> builder = new CustomBuilder<>();

        if (filterRequest.lowerPrice() != null && filterRequest.topPrice() != null) {
            builder.with(MultipleCriteria.builder().criterias(
                    SearchCriteria.OPERATOR_AND,
                    SearchCriteria.of("price", CustomSpecification.OPERATION_GREATER_THAN_EQUAL, filterRequest.lowerPrice()),
                    SearchCriteria.of("price", CustomSpecification.OPERATION_LESS_THAN_EQUAL, filterRequest.topPrice())
            ));
        } else if (filterRequest.topPrice() != null) {
            builder.with("price", CustomSpecification.OPERATION_LESS_THAN_EQUAL, filterRequest.topPrice());
        } else if (filterRequest.lowerPrice() != null) {
            builder.with("price", CustomSpecification.OPERATION_GREATER_THAN_EQUAL, filterRequest.lowerPrice());
        }

        Page<Food> listFood = foodRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listFood.stream().map(food -> {
            SimpleMap data = new SimpleMap();
            data.put("name", food.getName());
            data.put("decscription", food.getDescription());
            data.put("price", food.getPrice());
            data.put("category", food.getCategory());
            data.put("available", food.isAvailable());
            data.put("restaurant", food.getRestaurant());
            data.put("vegetarian", food.isVegetarian());
            data.put("seasonal", food.isSeasonal());
            data.put("ingredients", food.getIngredients());
            data.put("status", food.getStatus());
            data.put("listImage", food.getListImage().stream().map(FoodImage::getPath).collect(Collectors.toSet()));
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listFood.getTotalElements());
    }

    @Override
    public SimpleMap findById(String foodId) {
        var food = foodRepository.findById(foodId).orElseThrow(
                () -> new RuntimeException("Food not found")
        );

        SimpleMap data = new SimpleMap();
        data.put("name", food.getName());
        data.put("description", food.getDescription());
        data.put("price", food.getPrice());
        data.put("category", food.getCategory());
        data.put("available", food.isAvailable());
        data.put("restaurant", food.getRestaurant());
        data.put("vegetarian", food.isVegetarian());
        data.put("seasonal", food.isSeasonal());
        data.put("ingredients", food.getIngredients());
        data.put("status", food.getStatus());
        data.put("listImage", food.getListImage().stream().map(FoodImage::getPath).collect(Collectors.toSet()));
        return data;
    }
}