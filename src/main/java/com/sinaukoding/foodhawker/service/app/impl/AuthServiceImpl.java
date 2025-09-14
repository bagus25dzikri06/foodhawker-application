package com.sinaukoding.foodhawker.service.app.impl;

import com.sinaukoding.foodhawker.entity.managementuser.User;
import com.sinaukoding.foodhawker.mapper.managementuser.UserMapper;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.request.LoginRequestRecord;
import com.sinaukoding.foodhawker.model.request.UserRequestRecord;
import com.sinaukoding.foodhawker.repository.managementuser.UserRepository;
import com.sinaukoding.foodhawker.service.app.AuthService;
import com.sinaukoding.foodhawker.service.app.ValidatorService;
import com.sinaukoding.foodhawker.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ValidatorService validatorService;

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

    @Override
    public void register(UserRequestRecord request) {
        validasiMandatory(request);

        if (userRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException("E-mail [" + request.email() + "] had been used");
        }
        if (userRepository.existsByUsername(request.username().toLowerCase())) {
            throw new RuntimeException("Username [" + request.username() + "] had been used");
        }

        var user = userMapper.requestToEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    @Override
    public SimpleMap login(LoginRequestRecord request) {
        validatorService.validator(request);
        var user = userRepository.findByUsername(request.username().toLowerCase())
                .orElseThrow(
                        () -> new RuntimeException("Username atau password salah")
        );
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Username atau password salah");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        user.setToken(token);
        user.setExpiredTokenAt(LocalDateTime.now().plusHours(3));
        userRepository.save(user);
        SimpleMap result = new SimpleMap();
        result.put("token", token);
        return result;
    }

    @Override
    public void logout(User userLoggedIn) {
        userLoggedIn.setToken(null);
        userLoggedIn.setExpiredTokenAt(null);
        userRepository.save(userLoggedIn);
    }
}