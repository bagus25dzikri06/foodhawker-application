package com.sinaukoding.foodhawker.mapper.managementuser;

import com.sinaukoding.foodhawker.entity.managementuser.User;
import com.sinaukoding.foodhawker.model.request.UserRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User requestToEntity(UserRequestRecord request) {
        return User.builder()
                .nama(request.nama().toUpperCase())
                .username(request.username().toLowerCase())
                .email(request.email().toLowerCase())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .status(request.status())
                .role(request.role())
                .build();
    }
}