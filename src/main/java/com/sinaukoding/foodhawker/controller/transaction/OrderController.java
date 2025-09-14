package com.sinaukoding.foodhawker.controller.transaction;

import com.sinaukoding.foodhawker.model.filter.OrderFilterRecord;
import com.sinaukoding.foodhawker.model.filter.OrderItemFilterRecord;
import com.sinaukoding.foodhawker.model.request.OrderItemRequestRecord;
import com.sinaukoding.foodhawker.model.request.OrderRequestRecord;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import com.sinaukoding.foodhawker.service.transaction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("save")
    @PreAuthorize("hasRole('CUSTOMER')")
    public BaseResponse<?> save(@RequestBody OrderRequestRecord request) {
        orderService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('CUSTOMER')")
    public BaseResponse<?> edit(@RequestBody OrderRequestRecord request) {
        orderService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    @PreAuthorize("hasAnyRole('FOOD_VENDOR', 'ADMIN')")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody OrderFilterRecord filterRequest) {
        return BaseResponse.ok(null, orderService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasAnyRole('FOOD_VENDOR', 'ADMIN')")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, orderService.findById(id));
    }
}