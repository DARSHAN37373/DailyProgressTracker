package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.PlannerTaskRequestDTO;
import com.darshan.dailyprogress.dto.PlannerTaskResponseDTO;
import com.darshan.dailyprogress.service.PlannerTaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.darshan.dailyprogress.dto.ActualHoursRequestDTO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.darshan.dailyprogress.entity.PlannerStatus;

@RestController
@RequestMapping("/api/planner")
public class PlannerTaskController {

    private final PlannerTaskService plannerTaskService;

    public PlannerTaskController(PlannerTaskService plannerTaskService) {
        this.plannerTaskService = plannerTaskService;
    }

    @PostMapping
    public PlannerTaskResponseDTO createPlannerTask(
            @Valid @RequestBody PlannerTaskRequestDTO request) {

        return plannerTaskService.createPlannerTask(request);
    }

    @GetMapping
    public List<PlannerTaskResponseDTO> getAllPlannerTasks() {
        return plannerTaskService.getAllPlannerTasks();
    }

    // Filter Planner Tasks By Status
@GetMapping("/search")
public List<PlannerTaskResponseDTO> getPlannerTasksByStatus(
        @RequestParam PlannerStatus status) {

    return plannerTaskService.getPlannerTasksByStatus(status);
}


// Search Planner Tasks By Title
@GetMapping("/search/title")
public List<PlannerTaskResponseDTO> getPlannerTasksByTitle(
        @RequestParam String keyword) {

    return plannerTaskService.getPlannerTasksByTitle(keyword);
}


// Filter Planner Tasks by Status and Title
// with Pagination and Sorting
@GetMapping("/filter")
public Page<PlannerTaskResponseDTO> filterPlannerTasks(
        @RequestParam PlannerStatus status,
        @RequestParam String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "dueDate") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    return plannerTaskService.filterPlannerTasks(
            status,
            keyword,
            page,
            size,
            sortBy,
            direction
    );
}

    // Get Planner Tasks with Pagination and Sorting
@GetMapping("/page")
public Page<PlannerTaskResponseDTO> getPlannerTasksPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "dueDate") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    return plannerTaskService.getPlannerTasksPaginated(
            page,
            size,
            sortBy,
            direction
    );
}

    // Complete Planner Task
@PatchMapping("/{id}/complete")
public PlannerTaskResponseDTO completePlannerTask(
        @PathVariable Long id) {

    return plannerTaskService.completePlannerTask(id);
}
// Update Actual Hours
@PatchMapping("/{id}/hours")
public PlannerTaskResponseDTO updateActualHours(
        @PathVariable Long id,
        @Valid @RequestBody ActualHoursRequestDTO request) {

    return plannerTaskService.updateActualHours(
            id,
            request.getActualHours()
    );
}

    
    @GetMapping("/{id}")
    public PlannerTaskResponseDTO getPlannerTaskById(@PathVariable Long id) {
        return plannerTaskService.getPlannerTaskById(id);
    }

    @PutMapping("/{id}")
    public PlannerTaskResponseDTO updatePlannerTask(
            @PathVariable Long id,
            @Valid @RequestBody PlannerTaskRequestDTO request) {

        return plannerTaskService.updatePlannerTask(id, request);
    }

    @DeleteMapping("/{id}")
    public String deletePlannerTask(@PathVariable Long id) {

        plannerTaskService.deletePlannerTask(id);

        return "Planner Task deleted successfully";
    }
}