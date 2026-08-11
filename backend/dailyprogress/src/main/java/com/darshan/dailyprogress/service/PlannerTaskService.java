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

import com.darshan.dailyprogress.exception.PlannerTaskAlreadyCompletedException;
import com.darshan.dailyprogress.exception.ResourceNotFoundException;

import com.darshan.dailyprogress.dto.ActualHoursRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.darshan.dailyprogress.entity.PlannerStatus;
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
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

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
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return plannerTaskRepository.findByUser(user)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Filter Planner Tasks By Status
public List<PlannerTaskResponseDTO> getPlannerTasksByStatus(
        PlannerStatus status) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));

    return plannerTaskRepository.findByUserAndStatus(user, status)
            .stream()
            .map(this::convertToResponseDTO)
            .collect(Collectors.toList());
}


// Search Planner Tasks By Title
public List<PlannerTaskResponseDTO> getPlannerTasksByTitle(
        String keyword) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));

    return plannerTaskRepository
            .findByUserAndTitleContainingIgnoreCase(user, keyword)
            .stream()
            .map(this::convertToResponseDTO)
            .collect(Collectors.toList());
}


// Filter Planner Tasks by Status and Title
// with Pagination and Sorting
public Page<PlannerTaskResponseDTO> filterPlannerTasks(
        PlannerStatus status,
        String keyword,
        int page,
        int size,
        String sortBy,
        String direction) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    return plannerTaskRepository
            .findByUserAndStatusAndTitleContainingIgnoreCase(
                    user,
                    status,
                    keyword,
                    pageable
            )
            .map(this::convertToResponseDTO);
}

    // Get Planner Tasks with Pagination and Sorting
public Page<PlannerTaskResponseDTO> getPlannerTasksPaginated(
        int page,
        int size,
        String sortBy,
        String direction) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    return plannerTaskRepository.findByUser(user, pageable)
            .map(this::convertToResponseDTO);
}

    // Get Planner Task By Id
    public PlannerTaskResponseDTO getPlannerTaskById(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        PlannerTask plannerTask =
                plannerTaskRepository.findByIdAndUser(id, user)
                        .orElseThrow(() -> new ResourceNotFoundException("Planner Task not found"));

        return convertToResponseDTO(plannerTask);
    }

    // Update Planner Task
    public PlannerTaskResponseDTO updatePlannerTask(Long id,
                                                    PlannerTaskRequestDTO request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        PlannerTask plannerTask =
                plannerTaskRepository.findByIdAndUser(id, user)
                        .orElseThrow(() -> new ResourceNotFoundException("Planner Task not found"));

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


    // Complete Planner Task
public PlannerTaskResponseDTO completePlannerTask(Long id) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));

    PlannerTask plannerTask =
            plannerTaskRepository.findByIdAndUser(id, user)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Planner Task not found"));

    if (plannerTask.getStatus() == PlannerStatus.COMPLETED) {
        throw new PlannerTaskAlreadyCompletedException(
                "Planner task already completed");
    }

    if (plannerTask.getStatus() == PlannerStatus.POSTPONED ||
        plannerTask.getStatus() == PlannerStatus.CANCELLED) {

        throw new PlannerTaskAlreadyCompletedException(
                "Cannot complete a postponed or cancelled task");
    }

    plannerTask.setStatus(PlannerStatus.COMPLETED);

    PlannerTask completedTask =
            plannerTaskRepository.save(plannerTask);

    return convertToResponseDTO(completedTask);
}

        // Update Actual Hours
public PlannerTaskResponseDTO updateActualHours(
        Long id,
        Integer actualHours) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));

    PlannerTask plannerTask =
            plannerTaskRepository.findByIdAndUser(id, user)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Planner Task not found"));

    if (actualHours < 0) {
        throw new IllegalArgumentException(
                "Actual hours cannot be negative");
    }

    plannerTask.setActualHours(actualHours);

    PlannerTask updatedTask =
            plannerTaskRepository.save(plannerTask);

    return convertToResponseDTO(updatedTask);
}

    // Delete Planner Task
    public void deletePlannerTask(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        PlannerTask plannerTask =
                plannerTaskRepository.findByIdAndUser(id, user)
                        .orElseThrow(() -> new ResourceNotFoundException("Planner Task not found"));

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