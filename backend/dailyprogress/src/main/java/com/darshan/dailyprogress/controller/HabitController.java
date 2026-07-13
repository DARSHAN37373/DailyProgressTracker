package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.HabitRequestDTO;
import com.darshan.dailyprogress.dto.HabitResponseDTO;
import com.darshan.dailyprogress.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    // Create Habit
    @PostMapping
    public HabitResponseDTO createHabit(
            @Valid @RequestBody HabitRequestDTO request) {

        return habitService.createHabit(request);
    }

    // Get All Habits
    @GetMapping
    public List<HabitResponseDTO> getAllHabits() {

        return habitService.getAllHabits();
    }

    // Get Habit By Id
    @GetMapping("/{id}")
    public HabitResponseDTO getHabitById(
            @PathVariable Long id) {

        return habitService.getHabitById(id);
    }

    // Update Habit
    @PutMapping("/{id}")
    public HabitResponseDTO updateHabit(
            @PathVariable Long id,
            @Valid @RequestBody HabitRequestDTO request) {

        return habitService.updateHabit(id, request);
    }

    // Delete Habit
    @DeleteMapping("/{id}")
    public String deleteHabit(@PathVariable Long id) {

        habitService.deleteHabit(id);

        return "Habit deleted successfully";
    }
}