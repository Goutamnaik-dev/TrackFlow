package com.trackflow.trackflow.service;

import com.trackflow.trackflow.model.AppUser;
import com.trackflow.trackflow.repository.AppUserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser registerUser(String email, String password) {

        // Normalize email
        email = email.trim().toLowerCase();

        // Check whether email already exists
        if (appUserRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email is already registered!"
            );
        }

        // Create new user
        AppUser user = new AppUser();
        user.setEmail(email);

        // Hash password using BCrypt
        user.setPasswordHash(
                passwordEncoder.encode(password)
        );

        // Save user in database
        return appUserRepository.save(user);
    }
}