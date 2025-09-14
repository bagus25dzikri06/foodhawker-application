package com.sinaukoding.foodhawker.controller.app;

import com.sinaukoding.foodhawker.config.UserLoggedInConfig;
import com.sinaukoding.foodhawker.model.request.LoginRequestRecord;
import com.sinaukoding.foodhawker.model.request.UserRequestRecord;
import com.sinaukoding.foodhawker.model.response.BaseResponse;
import com.sinaukoding.foodhawker.service.app.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public BaseResponse<?> register(@RequestBody UserRequestRecord request) {
        authService.register(request);
        return BaseResponse.ok("User is successfully register", null);
    }

    @PostMapping("login")
    public BaseResponse<?> login(@RequestBody LoginRequestRecord request) {
        return BaseResponse.ok(null, authService.login(request));
    }

    @GetMapping("logout")
    public BaseResponse<?> logout(
            @AuthenticationPrincipal UserLoggedInConfig userLoggedInConfig
    ) {
        var userLoggedIn = userLoggedInConfig.getUser();
        authService.logout(userLoggedIn);
        return BaseResponse.ok("Successfully logout", null);
    }
}