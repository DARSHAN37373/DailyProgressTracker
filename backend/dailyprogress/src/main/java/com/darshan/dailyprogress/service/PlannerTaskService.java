package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.PlannerTaskRequestDTO;
import com.darshan.dailyprogress.dto.PlannerTaskResponseDTO;
import com.darshan.dailyprogress.entity.PlannerTask;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.repository.PlannerTaskRepository;
import com.darshan.dailyprogress.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlannerTaskService {

    private final PlannerTaskRepository plannerTaskRepository;
    private final UserRepository userRepository;

    public PlannerTaskService(PlannerTaskRepository plannerTaskRepository,
                              UserRepository userRepository) {

        this.plannerTaskRepository = plannerTaskRepository;
        this.userRepository = userRepository;
    }

    // Create Planner Task
    public PlannerTaskResponseDTO createPlannerTask(PlannerTaskRequestDTO request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlannerTask plannerTask = new PlannerTask();

        plannerTask.setUser(user);
        plannerTask.setTitle(request.getTitle());
        plannerTask.setDescription(request.getDescription());
        plannerTask.setCategory(request.getCategory());
        plannerTask.setPriority(request.getPriority());
        plannerTask.setStatus(request.getStatus());
        plannerTask.setStartDate(request.getStartDate());
        plannerTask.setDueDate(request.getDueDate());
        plannerTask.setReminderTime(request.getReminderTime());
        plannerTask.setEstimatedHours(request.getEstimatedHours());
        plannerTask.setRecurring(request.getRecurring());
        plannerTask.setActualHours(0);

        PlannerTask savedTask = plannerTaskRepository.save(plannerTask);

        return convertToResponseDTO(savedTask);
    }

    // Get All Planner Tasks
    public List<PlannerTaskResponseDTO> getAllPlannerTasks() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return plannerTaskRepository.findByUser(user)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get Planner Task By Id
    public PlannerTaskResponseDTO getPlannerTaskById(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlannerTask plannerTask =
                plannerTaskRepository.findByIdAndUser(id, user)
                        .orElseThrow(() -> new RuntimeException("Planner Task not found"));

        return convertToResponseDTO(plannerTask);
    }

    // Update Planner Task
    public PlannerTaskResponseDTO updatePlannerTask(Long id,
                                                    PlannerTaskRequestDTO request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlannerTask plannerTask =
                plannerTaskRepository.findByIdAndUser(id, user)
                        .orElseThrow(() -> new RuntimeException("Planner Task not found"));

        plannerTask.setTitle(request.getTitle());
        plannerTask.setDescription(request.getDescription());
        plannerTask.setCategory(request.getCategory());
        plannerTask.setPriority(request.getPriority());
        plannerTask.setStatus(request.getStatus());
        plannerTask.setStartDate(request.getStartDate());
        plannerTask.setDueDate(request.getDueDate());
        plannerTask.setReminderTime(request.getReminderTime());
        plannerTask.setEstimatedHours(request.getEstimatedHours());
        plannerTask.setRecurring(request.getRecurring());

        PlannerTask updatedTask = plannerTaskRepository.save(plannerTask);

        return convertToResponseDTO(updatedTask);
    }

    // Delete Planner Task
    public void deletePlannerTask(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlannerTask plannerTask =
                plannerTaskRepository.findByIdAndUser(id, user)
                        .orElseThrow(() -> new RuntimeException("Planner Task not found"));

        plannerTaskRepository.delete(plannerTask);
    }

    // Convert Entity → DTO
    private PlannerTaskResponseDTO convertToResponseDTO(PlannerTask plannerTask) {

        PlannerTaskResponseDTO response = new PlannerTaskResponseDTO();

        response.setId(plannerTask.getId());
        response.setTitle(plannerTask.getTitle());
        response.setDescription(plannerTask.getDescription());
        response.setCategory(plannerTask.getCategory());
        response.setPriority(plannerTask.getPriority());
        response.setStatus(plannerTask.getStatus());
        response.setStartDate(plannerTask.getStartDate());
        response.setDueDate(plannerTask.getDueDate());
        response.setReminderTime(plannerTask.getReminderTime());
        response.setEstimatedHours(plannerTask.getEstimatedHours());
        response.setActualHours(plannerTask.getActualHours());
        response.setRecurring(plannerTask.getRecurring());
        response.setCreatedAt(plannerTask.getCreatedAt());
        response.setUpdatedAt(plannerTask.getUpdatedAt());

        return response;
    }
}