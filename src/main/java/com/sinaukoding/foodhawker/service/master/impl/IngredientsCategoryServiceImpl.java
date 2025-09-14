package com.sinaukoding.foodhawker.service.master.impl;

import com.sinaukoding.foodhawker.builder.CustomBuilder;
import com.sinaukoding.foodhawker.entity.master.IngredientsCategory;
import com.sinaukoding.foodhawker.entity.master.IngredientsItem;
import com.sinaukoding.foodhawker.mapper.master.IngredientsCategoryMapper;
import com.sinaukoding.foodhawker.model.app.AppPage;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.IngredientsCategoryFilterRecord;
import com.sinaukoding.foodhawker.model.request.IngredientsCategoryRequestRecord;
import com.sinaukoding.foodhawker.repository.master.IngredientsCategoryRepository;
import com.sinaukoding.foodhawker.service.app.ValidatorService;
import com.sinaukoding.foodhawker.service.master.IngredientsCategoryService;
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
public class IngredientsCategoryServiceImpl implements IngredientsCategoryService {
    private IngredientsCategoryRepository ingredientsCategoryRepository;
    private ValidatorService validatorService;
    private IngredientsCategoryMapper ingredientsCategoryMapper;

    @Override
    public void add(IngredientsCategoryRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data kategori bahan");
            log.debug("Request data kategori bahan: {}", request);

            // validator mandatory
            validatorService.validator(request);

            var ingredientsCategory = ingredientsCategoryMapper.requestToEntity(request);
            ingredientsCategoryRepository.save(ingredientsCategory);

            log.info("Kategori bahan {} berhasil ditambahkan", request.name());
            log.trace("Tambah data kategori bahan berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data kategori bahan gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(IngredientsCategoryRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var ingredientsCategoryExisting = ingredientsCategoryRepository.findById(request.ingredientsCategoryId()).orElseThrow(
                () -> new RuntimeException("Ingredients category not found")
        );
        var ingredientsCategory = ingredientsCategoryMapper.requestToEntity(request);
        ingredientsCategory.setIngredientsCategoryId(ingredientsCategoryExisting.getIngredientsCategoryId());
        ingredientsCategoryRepository.save(ingredientsCategory);
    }

    @Override
    public Page<SimpleMap> findAll(IngredientsCategoryFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<IngredientsCategory> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankEqual("name", filterRequest.name(), builder);

        Page<IngredientsCategory> listIngredientsCategory = ingredientsCategoryRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listIngredientsCategory.stream().map(ingredientsCategory -> {
            SimpleMap data = new SimpleMap();
            data.put("name", ingredientsCategory.getName());
            data.put("restaurant", ingredientsCategory.getRestaurant());
            data.put("ingredients", ingredientsCategory.getIngredients());
            data.put("status", ingredientsCategory.getStatus());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listIngredientsCategory.getTotalElements());
    }

    @Override
    public SimpleMap findById(String ingredientsCategoryId) {
        var ingredientsCategory = ingredientsCategoryRepository.findById(ingredientsCategoryId).orElseThrow(
                () -> new RuntimeException("Ingredients category not found")
        );

        SimpleMap data = new SimpleMap();
        data.put("name", ingredientsCategory.getName());
        data.put("restaurant", ingredientsCategory.getRestaurant());
        data.put("ingredients", ingredientsCategory.getIngredients());
        data.put("status", ingredientsCategory.getStatus());
        return data;
    }
}