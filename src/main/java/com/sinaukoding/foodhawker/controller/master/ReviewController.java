package com.sinaukoding.foodhawker.controller.master;

import com.sinaukoding.foodhawker.model.filter.ReviewFilterRecord;
import com.sinaukoding.foodhawker.model.request.ReviewRequestRecord;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import com.sinaukoding.foodhawker.service.master.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("save")
    @PreAuthorize("hasRole('CUSTOMER')")
    public BaseResponse<?> save(@RequestBody ReviewRequestRecord request) {
        reviewService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('CUSTOMER')")
    public BaseResponse<?> edit(@RequestBody ReviewRequestRecord request) {
        reviewService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody ReviewFilterRecord filterRequest) {
        return BaseResponse.ok(null, reviewService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, reviewService.findById(id));
    }
}