package com.sinaukoding.foodhawker.service.master.impl;

import com.sinaukoding.foodhawker.builder.CustomBuilder;
import com.sinaukoding.foodhawker.entity.master.Category;
import com.sinaukoding.foodhawker.mapper.master.CategoryMapper;
import com.sinaukoding.foodhawker.model.app.AppPage;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.CategoryFilterRecord;
import com.sinaukoding.foodhawker.model.request.CategoryRequestRecord;
import com.sinaukoding.foodhawker.repository.master.CategoryRepository;
import com.sinaukoding.foodhawker.service.app.ValidatorService;
import com.sinaukoding.foodhawker.service.master.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ValidatorService validatorService;
    private CategoryMapper categoryMapper;

    @Override
    public void add(CategoryRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data kategori");
            log.debug("Request data kategori: {}", request);

            // validator mandatory
            validatorService.validator(request);

            var category = categoryMapper.requestToEntity(request);
            categoryRepository.save(category);

            log.info("Kategori {} berhasil ditambahkan", request.name());
            log.trace("Tambah data Kategori berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data Kategori gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(CategoryRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var categoryExisting = categoryRepository.findById(request.categoryId()).orElseThrow(() -> new RuntimeException("Produk tidak ditemukan"));
        var category = categoryMapper.requestToEntity(request);
        category.setCategoryId(categoryExisting.getCategoryId());
        categoryRepository.save(category);
    }

    @Override
    public Page<SimpleMap> findAll(CategoryFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Category> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankEqual("name", filterRequest.name(), builder);
        FilterUtil.builderConditionNotBlankEqual("restaurantId", filterRequest.restaurantId().toString(), builder);
        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);

        Page<Category> listCategory = categoryRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listCategory.stream().map(category -> {
            SimpleMap data = new SimpleMap();
            data.put("name", category.getName());
            data.put("status", category.getStatus());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listCategory.getTotalElements());
    }

    @Override
    public SimpleMap findById(String categoryId) {
        var category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category not found")
        );

        SimpleMap data = new SimpleMap();
        data.put("name", category.getName());
        data.put("status", category.getStatus());
        return data;
    }
}