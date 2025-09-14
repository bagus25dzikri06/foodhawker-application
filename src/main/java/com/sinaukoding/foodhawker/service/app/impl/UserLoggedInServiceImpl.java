package com.sinaukoding.foodhawker.service.app.impl;

import com.sinaukoding.foodhawker.config.UserLoggedInConfig;
import com.sinaukoding.foodhawker.repository.managementuser.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoggedInServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " tidak ditemukan")
                );
        return new UserLoggedInConfig(user);
    }
}