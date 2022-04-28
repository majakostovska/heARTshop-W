package com.example.heartshop.service.impl;

import com.example.heartshop.model.Role;
import com.example.heartshop.model.User;
import com.example.heartshop.model.exceptions.*;
import com.example.heartshop.repository.UserRepositoryJPA;
import com.example.heartshop.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepositoryJPA userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryJPA userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,userRole);
        return userRepository.save(user);
    }

}
