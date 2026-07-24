package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.UserRequestDTO;
import com.darshan.dailyprogress.dto.UserResponseDTO;
import com.darshan.dailyprogress.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
@ResponseStatus(HttpStatus.CREATED)
public UserResponseDTO createUser(
        @Valid @RequestBody UserRequestDTO request) {
    return userService.saveUser(request);
}
    }
