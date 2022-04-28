package com.example.heartshop.service.impl;

import com.example.heartshop.model.User;
import com.example.heartshop.model.exceptions.InvalidArgumentException;
import com.example.heartshop.model.exceptions.InvalidCredentialException;
import com.example.heartshop.repository.UserRepositoryJPA;
import com.example.heartshop.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepositoryJPA userRepository;

    public AuthServiceImpl(UserRepositoryJPA userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidCredentialException::new);
    }

}