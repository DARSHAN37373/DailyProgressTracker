package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.UserRequestDTO;
import com.darshan.dailyprogress.dto.UserResponseDTO;
import com.darshan.dailyprogress.entity.AccountVisibility;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.entity.UserRole;
import com.darshan.dailyprogress.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO saveUser(UserRequestDTO request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setBio(request.getBio());
        user.setProfilePicture(request.getProfilePicture());

        user.setRole(UserRole.USER);
        user.setVisibility(AccountVisibility.PUBLIC);
        user.setActive(true);

        User savedUser = userRepository.save(user);

        return convertToResponseDTO(savedUser);
    }

    private UserResponseDTO convertToResponseDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setBio(user.getBio());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setRole(user.getRole().name());
        dto.setVisibility(user.getVisibility().name());

        return dto;
    }
}