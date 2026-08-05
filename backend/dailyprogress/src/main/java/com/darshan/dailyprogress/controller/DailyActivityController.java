package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.DailyActivityRequestDTO;
import com.darshan.dailyprogress.dto.DailyActivityResponseDTO;
import com.darshan.dailyprogress.service.DailyActivityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.Page;

import com.darshan.dailyprogress.entity.ActivityStatus;
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

        // Filter Activities By Status
@GetMapping("/search")
public List<DailyActivityResponseDTO> getActivitiesByStatus(
        @RequestParam ActivityStatus status) {

    return dailyActivityService.getActivitiesByStatus(status);
}


// Search Activities By Title
@GetMapping("/search/title")
public List<DailyActivityResponseDTO> getActivitiesByTitle(
        @RequestParam String keyword) {

    return dailyActivityService.getActivitiesByTitle(keyword);
}


// Filter Activities by Status and Title
// with Pagination and Sorting
@GetMapping("/filter")
public Page<DailyActivityResponseDTO> filterActivities(
        @RequestParam ActivityStatus status,
        @RequestParam String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "activityDate") String sortBy,
        @RequestParam(defaultValue = "desc") String direction) {

    return dailyActivityService.filterActivities(
            status,
            keyword,
            page,
            size,
            sortBy,
            direction
    );
}
    // Get Activities with Pagination and Sorting
@GetMapping("/page")
public Page<DailyActivityResponseDTO> getActivitiesPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "activityDate") String sortBy,
        @RequestParam(defaultValue = "desc") String direction) {

    return dailyActivityService.getActivitiesPaginated(
            page,
            size,
            sortBy,
            direction
    );
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