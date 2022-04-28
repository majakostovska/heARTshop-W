package com.example.heartshop.service;

import com.example.heartshop.model.User;

public interface AuthService {
    User login(String username, String password);
}
