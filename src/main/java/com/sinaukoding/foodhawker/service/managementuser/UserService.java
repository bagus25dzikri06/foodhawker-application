package com.sinaukoding.foodhawker.service.managementuser;

import com.sinaukoding.foodhawker.model.app.SimpleMap;
import com.sinaukoding.foodhawker.model.filter.UserFilterRecord;
import com.sinaukoding.foodhawker.model.request.UserRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    void edit(UserRequestRecord request);
    Page<SimpleMap> findAll(UserFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
}