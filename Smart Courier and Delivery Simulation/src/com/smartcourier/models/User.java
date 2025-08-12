package com.smartcourier.models;

import com.smartcourier.enums.UserRole;

public class User {
    private String username;
    private String password; // In a real app, this should be hashed
    private UserRole role;

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }
}