package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.GoalRequestDTO;
import com.darshan.dailyprogress.dto.GoalResponseDTO;
import com.darshan.dailyprogress.service.GoalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    // Create Goal
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoalResponseDTO createGoal(@Valid @RequestBody GoalRequestDTO request) {
        return goalService.createGoal(request);
    }
    // Get All Goals
    @GetMapping
    public List<GoalResponseDTO> getAllGoals() {
        return goalService.getAllGoals();
    }
// Get Goals with Pagination and Sorting
@GetMapping("/page")
public Page<GoalResponseDTO> getGoalsPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "targetDate") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    return goalService.getGoalsPaginated(
            page,
            size,
            sortBy,
            direction
    );
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