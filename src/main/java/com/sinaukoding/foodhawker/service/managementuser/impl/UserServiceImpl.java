package com.sinaukoding.foodhawker.service.managementuser.impl;

import com.sinaukoding.foodhawker.builder.CustomBuilder;
import com.sinaukoding.foodhawker.model.app.AppPage;
import com.sinaukoding.foodhawker.entity.managementuser.User;
import com.sinaukoding.foodhawker.mapper.managementuser.UserMapper;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.UserFilterRecord;
import com.sinaukoding.foodhawker.model.request.UserRequestRecord;
import com.sinaukoding.foodhawker.repository.managementuser.UserRepository;
import com.sinaukoding.foodhawker.service.managementuser.UserService;
import com.sinaukoding.foodhawker.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private void validasiMandatory(UserRequestRecord request) {
        if (request.nama() == null || request.nama().isEmpty()) {
            throw new RuntimeException("Name may not be empty");
        }
        if (request.username() == null || request.username().isEmpty()) {
            throw new RuntimeException("Username may not be empty");
        }
        if (request.email() == null || request.email().isEmpty()) {
            throw new RuntimeException("E-mail may not be empty");
        }
        if (request.phoneNumber() == null || request.phoneNumber().isEmpty()) {
            throw new RuntimeException("Phone number may not be empty");
        }
        if (request.address() == null || request.address().isEmpty()) {
            throw new RuntimeException("Address may not be empty");
        }
        if (request.password() == null || request.password().isEmpty()) {
            throw new RuntimeException("Email tidak boleh kosong");
        }
        if (request.status() == null) {
            throw new RuntimeException("Status may not be empty");
        }
        if (request.role() == null) {
            throw new RuntimeException("Role may not be empty");
        }
    }

    /*@Override
    public void add(UserRequestRecord request) {
        validasiMandatory(request);

        if (userRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException("E-mail [" + request.email() + "] had been used");
        }
        if (userRepository.existsByUsername(request.username().toLowerCase())) {
            throw new RuntimeException("Username [" + request.email() + "] had been used");
        }

        var user = userMapper.requestToEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }*/

    @Override
    public void edit(UserRequestRecord request) {
        validasiMandatory(request);

        var userExisting = userRepository
                .findById(request.id())
                .orElseThrow(() -> new RuntimeException("Data user not found"));

        if (userRepository.existsByEmailAndIdNot(request.email().toLowerCase(), request.id())) {
            throw new RuntimeException("E-mail [" + request.email() + "] had been used");
        }
        if (userRepository.existsByUsernameAndIdNot(request.username().toLowerCase(), request.id())) {
            throw new RuntimeException("Username [" + request.email() + "] had been used");
        }

        var user = userMapper.requestToEntity(request);
        user.setId(userExisting.getId());
        userRepository.save(user);
    }

    @Override
    public Page<SimpleMap> findAll(UserFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<User> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("nama", filterRequest.nama(), builder);
        FilterUtil.builderConditionNotBlankLike("username", filterRequest.username(), builder);
        FilterUtil.builderConditionNotBlankLike("email", filterRequest.email(), builder);
        FilterUtil.builderConditionNotBlankLike("phoneNumber", filterRequest.phoneNumber(), builder);
        FilterUtil.builderConditionNotBlankLike("address", filterRequest.address(), builder);
        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);
        FilterUtil.builderConditionNotNullEqual("role", filterRequest.role(), builder);

        Page<User> listUser = userRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listUser.stream().map(user -> {
            SimpleMap data = new SimpleMap();
            data.put("id", user.getId());
            data.put("nama", user.getNama());
            data.put("username", user.getUsername());
            data.put("email", user.getEmail());
            data.put("phoneNumber", user.getPhoneNumber());
            data.put("address", user.getAddress());
            data.put("role", user.getStatus().getLabel());
            data.put("status", user.getRole().getLabel());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listUser.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Data user not found"));
        SimpleMap data = new SimpleMap();
        data.put("id", user.getId());
        data.put("nama", user.getNama());
        data.put("username", user.getUsername());
        data.put("email", user.getEmail());
        data.put("phoneNumber", user.getPhoneNumber());
        data.put("address", user.getAddress());
        data.put("role", user.getStatus().getLabel());
        data.put("status", user.getRole().getLabel());
        return data;
    }
}