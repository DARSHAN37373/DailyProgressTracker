package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.GoalRequestDTO;
import com.darshan.dailyprogress.dto.GoalResponseDTO;
import com.darshan.dailyprogress.entity.Goal;
import com.darshan.dailyprogress.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



@Service
public class GoalService {

    private final GoalRepository goalRepository;

    private final UserRepository userRepository;

    public GoalService(GoalRepository goalRepository,
                   UserRepository userRepository) {

    this.goalRepository = goalRepository;
    this.userRepository = userRepository;
}

    
    // Create Goal
public GoalResponseDTO createGoal(GoalRequestDTO request) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Goal goal = new Goal();

    
    goal.setUser(user);
    goal.setTitle(request.getTitle());
    goal.setDescription(request.getDescription());
    goal.setTargetDate(request.getTargetDate());
    goal.setStatus(request.getStatus());

    Goal savedGoal = goalRepository.save(goal);

    return convertToResponseDTO(savedGoal);
}

    // Get All Goals
    public List<GoalResponseDTO> getAllGoals() {

        Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return goalRepository.findByUser(user)
            .stream()
            .map(this::convertToResponseDTO)
            .collect(Collectors.toList());
}

public GoalResponseDTO getGoalById(Long id) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Goal goal = goalRepository.findByIdAndUser(id, user)
            .orElseThrow(() -> new RuntimeException("Goal not found"));

    return convertToResponseDTO(goal);
}

    // Update Goal
    public GoalResponseDTO updateGoal(Long id, GoalRequestDTO request) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Goal goal = goalRepository.findByIdAndUser(id, user)
            .orElseThrow(() -> new RuntimeException("Goal not found"));

    goal.setTitle(request.getTitle());
    goal.setDescription(request.getDescription());
    goal.setTargetDate(request.getTargetDate());
    goal.setStatus(request.getStatus());

    Goal updatedGoal = goalRepository.save(goal);

    return convertToResponseDTO(updatedGoal);
}

    // Delete Goal
    public void deleteGoal(Long id) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Goal goal = goalRepository.findByIdAndUser(id, user)
            .orElseThrow(() -> new RuntimeException("Goal not found"));

    goalRepository.delete(goal);
}

    // Convert Entity → DTO
    private GoalResponseDTO convertToResponseDTO(Goal goal) {

        GoalResponseDTO response = new GoalResponseDTO();

        response.setId(goal.getId());
        response.setTitle(goal.getTitle());
        response.setDescription(goal.getDescription());
        response.setTargetDate(goal.getTargetDate());
        response.setStatus(goal.getStatus());

        return response;
    }
}