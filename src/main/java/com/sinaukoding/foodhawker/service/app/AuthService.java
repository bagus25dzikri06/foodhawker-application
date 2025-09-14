package com.sinaukoding.foodhawker.service.app;

import com.sinaukoding.foodhawker.entity.managementuser.User;
import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.request.LoginRequestRecord;
import com.sinaukoding.foodhawker.model.request.UserRequestRecord;

public interface AuthService {
    void register(UserRequestRecord request);
    SimpleMap login(LoginRequestRecord request);
    void logout(User userLoggedIn);
}