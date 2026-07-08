package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.LoginRequestDTO;
import com.darshan.dailyprogress.dto.LoginResponseDTO;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        LoginResponseDTO response = new LoginResponseDTO();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setMessage("Login successful");

        return response;
    }
}