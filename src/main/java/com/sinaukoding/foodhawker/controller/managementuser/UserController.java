package com.sinaukoding.foodhawker.controller.managementuser;

import com.sinaukoding.foodhawker.model.filter.UserFilterRecord;
import com.sinaukoding.foodhawker.model.request.UserRequestRecord;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import com.sinaukoding.foodhawker.service.managementuser.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("edit")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'FOOD_VENDOR')")
    public BaseResponse<?> edit(@RequestBody UserRequestRecord request) {
        userService.edit(request);
        return BaseResponse.ok("Data is successfully edited", null);
    }

    @PostMapping("find-all")
    public BaseResponse<?> findAll(
            @PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
            @RequestBody UserFilterRecord filterRequest
    ) {
        return BaseResponse.ok(null, userService.findAll(filterRequest, pageable));
    }

    @PostMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, userService.findById(id));
    }
}