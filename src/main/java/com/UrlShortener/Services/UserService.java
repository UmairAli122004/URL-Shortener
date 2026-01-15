package com.UrlShortener.Services;
import com.UrlShortener.DTOs.CreateUserCmd;
import com.UrlShortener.Entities.User;
import com.UrlShortener.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(CreateUserCmd cmd) {
        if (userRepository.existsByEmail(cmd.email())) {
            throw new RuntimeException("Email already exists");
        }
        var user = new User();
        user.setEmail(cmd.email());
        user.setPassword(passwordEncoder.encode(cmd.password()));
        user.setName(cmd.name());
        user.setRole(cmd.role());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}