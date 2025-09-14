package com.sinaukoding.foodhawker.controller.master;

import com.sinaukoding.foodhawker.model.filter.CategoryFilterRecord;
import com.sinaukoding.foodhawker.model.request.CategoryRequestRecord;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import com.sinaukoding.foodhawker.service.master.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("save")
    @PreAuthorize("hasRole('FOOD_VENDOR')")
    public BaseResponse<?> save(@RequestBody CategoryRequestRecord request) {
        categoryService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('FOOD_VENDOR')")
    public BaseResponse<?> edit(@RequestBody CategoryRequestRecord request) {
        categoryService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody CategoryFilterRecord filterRequest) {
        return BaseResponse.ok(null, categoryService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, categoryService.findById(id));
    }
}