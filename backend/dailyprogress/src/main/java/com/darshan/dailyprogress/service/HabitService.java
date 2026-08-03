package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.HabitRequestDTO;
import com.darshan.dailyprogress.dto.HabitResponseDTO;
import com.darshan.dailyprogress.entity.Habit;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.exception.ResourceNotFoundException;
import com.darshan.dailyprogress.repository.HabitRepository;
import com.darshan.dailyprogress.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository,
                        UserRepository userRepository) {

        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    // Create Habit
    
public HabitResponseDTO createHabit(HabitRequestDTO request) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    Habit habit = new Habit();

    habit.setUser(user);
    habit.setName(request.getName());
    habit.setDescription(request.getDescription());
    habit.setFrequency(request.getFrequency());
    habit.setStatus(request.getStatus());
    habit.setTargetCount(request.getTargetCount());

    habit.setCompletedCount(0);
    habit.setCurrentStreak(0);
    habit.setLongestStreak(0);
    habit.setCreatedDate(LocalDate.now());

    Habit savedHabit = habitRepository.save(habit);

    return convertToResponseDTO(savedHabit);
}

    // Get All Habits
    public List<HabitResponseDTO> getAllHabits() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return habitRepository.findByUser(user)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get Habits with Pagination and Sorting
public Page<HabitResponseDTO> getHabitsPaginated(
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

    return habitRepository.findByUser(user, pageable)
            .map(this::convertToResponseDTO);
}

    // Get Habit By Id
    public HabitResponseDTO getHabitById(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Habit habit = habitRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));

        return convertToResponseDTO(habit);
    }

    // Update Habit
    public HabitResponseDTO updateHabit(Long id,
                                        HabitRequestDTO request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Habit habit = habitRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));

        habit.setName(request.getName());
        habit.setDescription(request.getDescription());
        habit.setFrequency(request.getFrequency());
        habit.setStatus(request.getStatus());
        habit.setTargetCount(request.getTargetCount());

        Habit updatedHabit = habitRepository.save(habit);

        return convertToResponseDTO(updatedHabit);
    }

    // Delete Habit
    public void deleteHabit(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Habit habit = habitRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));

        habitRepository.delete(habit);
    }

    // Convert Entity → DTO
    private HabitResponseDTO convertToResponseDTO(Habit habit) {

        HabitResponseDTO response = new HabitResponseDTO();

        response.setId(habit.getId());
        response.setName(habit.getName());
        response.setDescription(habit.getDescription());
        response.setFrequency(habit.getFrequency());
        response.setStatus(habit.getStatus());
        response.setTargetCount(habit.getTargetCount());
        response.setCompletedCount(habit.getCompletedCount());
        response.setCurrentStreak(habit.getCurrentStreak());
        response.setLongestStreak(habit.getLongestStreak());
        response.setCreatedDate(habit.getCreatedDate());

        return response;
    }
}