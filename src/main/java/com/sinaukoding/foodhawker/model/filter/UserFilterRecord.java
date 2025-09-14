package com.sinaukoding.foodhawker.model.filter;

import com.sinaukoding.foodhawker.model.enums.Role;
import com.sinaukoding.foodhawker.model.enums.Status;

public record UserFilterRecord(
        String nama,
        String username,
        String email,
        String phoneNumber,
        String address,
        Status status,
        Role role
) {
}