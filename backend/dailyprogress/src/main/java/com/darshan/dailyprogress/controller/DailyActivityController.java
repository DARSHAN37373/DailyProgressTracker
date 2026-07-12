package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.DailyActivityRequestDTO;
import com.darshan.dailyprogress.dto.DailyActivityResponseDTO;
import com.darshan.dailyprogress.service.DailyActivityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class DailyActivityController {

    private final DailyActivityService dailyActivityService;

    public DailyActivityController(DailyActivityService dailyActivityService) {
        this.dailyActivityService = dailyActivityService;
    }

    // Create Activity
    @PostMapping
    public DailyActivityResponseDTO createActivity(
            @Valid @RequestBody DailyActivityRequestDTO request) {

        return dailyActivityService.createActivity(request);
    }

    // Get All Activities
    @GetMapping
    public List<DailyActivityResponseDTO> getAllActivities() {

        return dailyActivityService.getAllActivities();
    }

    // Get Activity By Id
    @GetMapping("/{id}")
    public DailyActivityResponseDTO getActivityById(
            @PathVariable Long id) {

        return dailyActivityService.getActivityById(id);
    }

    // Update Activity
    @PutMapping("/{id}")
    public DailyActivityResponseDTO updateActivity(
            @PathVariable Long id,
            @Valid @RequestBody DailyActivityRequestDTO request) {

        return dailyActivityService.updateActivity(id, request);
    }

    // Delete Activity
    @DeleteMapping("/{id}")
    public String deleteActivity(@PathVariable Long id) {

        dailyActivityService.deleteActivity(id);

        return "Activity deleted successfully";
    }
}