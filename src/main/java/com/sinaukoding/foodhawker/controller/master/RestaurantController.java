package com.sinaukoding.foodhawker.controller.master;

import com.sinaukoding.foodhawker.model.filter.RestaurantFilterRecord;
import com.sinaukoding.foodhawker.model.request.RestaurantRequestRecord;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import com.sinaukoding.foodhawker.service.master.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("save")
    @PreAuthorize("hasRole('FOOD_VENDOR')")
    public BaseResponse<?> save(@RequestBody RestaurantRequestRecord request) {
        restaurantService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('FOOD_VENDOR')")
    public BaseResponse<?> edit(@RequestBody RestaurantRequestRecord request) {
        restaurantService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody RestaurantFilterRecord filterRequest) {
        return BaseResponse.ok(null, restaurantService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, restaurantService.findById(id));
    }
}