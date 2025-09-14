package com.sinaukoding.foodhawker.controller.master;

import com.sinaukoding.foodhawker.model.filter.IngredientsCategoryFilterRecord;
import com.sinaukoding.foodhawker.model.request.IngredientsCategoryRequestRecord;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import com.sinaukoding.foodhawker.service.master.IngredientsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ingredients-category")
@RequiredArgsConstructor
public class IngredientsCategoryController {
    private final IngredientsCategoryService ingredientsCategoryService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> save(@RequestBody IngredientsCategoryRequestRecord request) {
        ingredientsCategoryService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> edit(@RequestBody IngredientsCategoryRequestRecord request) {
        ingredientsCategoryService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody IngredientsCategoryFilterRecord filterRequest) {
        return BaseResponse.ok(null, ingredientsCategoryService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, ingredientsCategoryService.findById(id));
    }
}