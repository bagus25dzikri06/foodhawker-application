package com.sinaukoding.foodhawker.controller.master;

import com.sinaukoding.foodhawker.model.filter.IngredientsItemFilterRecord;
import com.sinaukoding.foodhawker.model.request.IngredientsItemRequestRecord;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import com.sinaukoding.foodhawker.service.master.IngredientsItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ingredients-item")
@RequiredArgsConstructor
public class IngredientsItemController {
    private final IngredientsItemService ingredientsItemService;

    @PostMapping("save")
    @PreAuthorize("hasRole('FOOD_VENDOR')")
    public BaseResponse<?> save(@RequestBody IngredientsItemRequestRecord request) {
        ingredientsItemService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('FOOD_VENDOR')")
    public BaseResponse<?> edit(@RequestBody IngredientsItemRequestRecord request) {
        ingredientsItemService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody IngredientsItemFilterRecord filterRequest) {
        return BaseResponse.ok(null, ingredientsItemService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, ingredientsItemService.findById(id));
    }
}