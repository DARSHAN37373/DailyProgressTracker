package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.GoalRequestDTO;
import com.darshan.dailyprogress.dto.GoalResponseDTO;
import com.darshan.dailyprogress.entity.Goal;
import com.darshan.dailyprogress.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    // Create Goal
    public GoalResponseDTO createGoal(GoalRequestDTO request) {

        Goal goal = new Goal();

        goal.setTitle(request.getTitle());
        goal.setDescription(request.getDescription());
        goal.setTargetDate(request.getTargetDate());
        goal.setStatus(request.getStatus());

        Goal savedGoal = goalRepository.save(goal);

        return convertToResponseDTO(savedGoal);
    }

    // Get All Goals
    public List<GoalResponseDTO> getAllGoals() {
        return goalRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get Goal By Id
    public GoalResponseDTO getGoalById(Long id) {

        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        return convertToResponseDTO(goal);
    }

    // Update Goal
    public GoalResponseDTO updateGoal(Long id, GoalRequestDTO request) {

        Goal goal = goalRepository.findById(id)
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

        Goal goal = goalRepository.findById(id)
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