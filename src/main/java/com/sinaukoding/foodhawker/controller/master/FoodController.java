package com.sinaukoding.foodhawker.controller.master;

import com.sinaukoding.foodhawker.model.filter.FoodFilterRecord;
import com.sinaukoding.foodhawker.model.request.FoodRequestRecord;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import com.sinaukoding.foodhawker.service.master.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @PostMapping("save")
    @PreAuthorize("hasRole('FOOD_VENDOR')")
    public BaseResponse<?> save(@RequestBody FoodRequestRecord request) {
        foodService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('FOOD_VENDOR')")
    public BaseResponse<?> edit(@RequestBody FoodRequestRecord request) {
        foodService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody FoodFilterRecord filterRequest) {
        return BaseResponse.ok(null, foodService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, foodService.findById(id));
    }
}