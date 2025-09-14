package com.sinaukoding.foodhawker.service.master.impl;

import com.sinaukoding.foodhawker.builder.CustomBuilder;
import com.sinaukoding.foodhawker.entity.master.IngredientsItem;
import com.sinaukoding.foodhawker.mapper.master.IngredientsItemMapper;
import com.sinaukoding.foodhawker.model.app.AppPage;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.IngredientsItemFilterRecord;
import com.sinaukoding.foodhawker.model.request.IngredientsItemRequestRecord;
import com.sinaukoding.foodhawker.repository.master.IngredientsItemRepository;
import com.sinaukoding.foodhawker.service.app.ValidatorService;
import com.sinaukoding.foodhawker.service.master.IngredientsItemService;
import com.sinaukoding.foodhawker.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientsItemServiceImpl implements IngredientsItemService {
    private IngredientsItemRepository ingredientsItemRepository;
    private ValidatorService validatorService;
    private IngredientsItemMapper ingredientsItemMapper;

    @Override
    public void add(IngredientsItemRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data bahan");
            log.debug("Request data bahan: {}", request);

            // validator mandatory
            validatorService.validator(request);

            var ingredientsItem = ingredientsItemMapper.requestToEntity(request);
            ingredientsItemRepository.save(ingredientsItem);

            log.info("Bahan {} berhasil ditambahkan", request.name());
            log.trace("Tambah data bahan berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data bahan gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(IngredientsItemRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var ingredientsItemExisting = ingredientsItemRepository.findById(request.id()).orElseThrow(
                () -> new RuntimeException("Ingredients item not found")
        );
        var ingredientsItem = ingredientsItemMapper.requestToEntity(request);
        ingredientsItem.setId(ingredientsItemExisting.getId());
        ingredientsItemRepository.save(ingredientsItem);
    }

    @Override
    public Page<SimpleMap> findAll(IngredientsItemFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<IngredientsItem> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankEqual("name", filterRequest.name(), builder);

        Page<IngredientsItem> listIngredientsItem = ingredientsItemRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listIngredientsItem.stream().map(ingredientsItem -> {
            SimpleMap data = new SimpleMap();
            data.put("name", ingredientsItem.getName());
            data.put("ingredientsCategory", ingredientsItem.getIngredientsCategory());
            data.put("restaurant", ingredientsItem.getRestaurant());
            data.put("status", ingredientsItem.getStatus());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listIngredientsItem.getTotalElements());
    }

    @Override
    public SimpleMap findById(String ingredientsItemsId) {
        var ingredientsItem = ingredientsItemRepository.findById(ingredientsItemsId).orElseThrow(
                () -> new RuntimeException("Ingredients item not found")
        );

        SimpleMap data = new SimpleMap();
        data.put("name", ingredientsItem.getName());
        data.put("ingredientsCategory", ingredientsItem.getIngredientsCategory());
        data.put("restaurant", ingredientsItem.getRestaurant());
        data.put("status", ingredientsItem.getStatus());
        return data;
    }
}