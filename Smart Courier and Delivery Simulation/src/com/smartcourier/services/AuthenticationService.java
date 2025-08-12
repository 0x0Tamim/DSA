package com.smartcourier.services;

import com.smartcourier.enums.UserRole;
import com.smartcourier.models.User;
import com.smartcourier.repositories.UserRepository;
import java.util.Optional;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String password, UserRole role) {
        if (userRepository.findByUsername(username).isPresent()) {
            System.out.println("Error: Username already exists.");
            return null;
        }
        User newUser = new User(username, password, role);
        userRepository.save(newUser);
        System.out.println("User registered successfully: " + username);
        return newUser;
    }

    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            System.out.println("Login successful for " + username);
            return userOpt;
        }
        System.out.println("Error: Invalid username or password.");
        return Optional.empty();
    }
}