package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.PlannerTaskRequestDTO;
import com.darshan.dailyprogress.dto.PlannerTaskResponseDTO;
import com.darshan.dailyprogress.service.PlannerTaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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