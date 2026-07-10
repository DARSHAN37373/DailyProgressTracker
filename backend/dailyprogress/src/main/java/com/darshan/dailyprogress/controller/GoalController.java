package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.GoalRequestDTO;
import com.darshan.dailyprogress.dto.GoalResponseDTO;
import com.darshan.dailyprogress.service.GoalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    // Create Goal
    @PostMapping
    public GoalResponseDTO createGoal(@Valid @RequestBody GoalRequestDTO request) {
        return goalService.createGoal(request);
    }

    // Get All Goals
    @GetMapping
    public List<GoalResponseDTO> getAllGoals() {
        return goalService.getAllGoals();
    }

    // Get Goal By Id
    @GetMapping("/{id}")
    public GoalResponseDTO getGoalById(@PathVariable Long id) {
        return goalService.getGoalById(id);
    }

    // Update Goal
    @PutMapping("/{id}")
    public GoalResponseDTO updateGoal(
            @PathVariable Long id,
            @Valid @RequestBody GoalRequestDTO request) {

        return goalService.updateGoal(id, request);
    }

    // Delete Goal
    @DeleteMapping("/{id}")
    public String deleteGoal(@PathVariable Long id) {

        goalService.deleteGoal(id);

        return "Goal deleted successfully";
    }
}