package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.HabitRequestDTO;
import com.darshan.dailyprogress.dto.HabitResponseDTO;
import com.darshan.dailyprogress.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

import java.util.List;

import com.darshan.dailyprogress.entity.HabitStatus;
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

        // Filter Habits By Status
@GetMapping("/search")
public List<HabitResponseDTO> getHabitsByStatus(
        @RequestParam HabitStatus status) {

    return habitService.getHabitsByStatus(status);
}


// Search Habits By Name
@GetMapping("/search/name")
public List<HabitResponseDTO> getHabitsByName(
        @RequestParam String keyword) {

    return habitService.getHabitsByName(keyword);
}


// Filter Habits by Status and Name with Pagination and Sorting
@GetMapping("/filter")
public Page<HabitResponseDTO> filterHabits(
        @RequestParam HabitStatus status,
        @RequestParam String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "createdDate") String sortBy,
        @RequestParam(defaultValue = "desc") String direction) {

    return habitService.filterHabits(
            status,
            keyword,
            page,
            size,
            sortBy,
            direction
    );
}
    // Get Habits with Pagination and Sorting
@GetMapping("/page")
public Page<HabitResponseDTO> getHabitsPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "createdDate") String sortBy,
        @RequestParam(defaultValue = "desc") String direction) {

    return habitService.getHabitsPaginated(
            page,
            size,
            sortBy,
            direction
    );
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