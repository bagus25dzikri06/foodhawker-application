package com.sinaukoding.foodhawker.model.request;

import com.sinaukoding.foodhawker.model.enums.Role;
import com.sinaukoding.foodhawker.model.enums.Status;

public record UserRequestRecord(
        String id,
        String nama,
        String username,
        String email,
        String phoneNumber,
        String address,
        String password,
        Status status,
        Role role
) {
}