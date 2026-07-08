package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.LoginRequestDTO;
import com.darshan.dailyprogress.dto.LoginResponseDTO;
import com.darshan.dailyprogress.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}