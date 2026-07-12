package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.DailyActivityRequestDTO;
import com.darshan.dailyprogress.dto.DailyActivityResponseDTO;
import com.darshan.dailyprogress.entity.DailyActivity;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.repository.DailyActivityRepository;
import com.darshan.dailyprogress.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DailyActivityService {

    private final DailyActivityRepository dailyActivityRepository;
    private final UserRepository userRepository;

    public DailyActivityService(DailyActivityRepository dailyActivityRepository,
                                UserRepository userRepository) {

        this.dailyActivityRepository = dailyActivityRepository;
        this.userRepository = userRepository;
    }

    // Create Activity
    public DailyActivityResponseDTO createActivity(DailyActivityRequestDTO request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DailyActivity activity = new DailyActivity();

        activity.setUser(user);
        activity.setTitle(request.getTitle());
        activity.setDescription(request.getDescription());
        activity.setCategory(request.getCategory());
        activity.setStatus(request.getStatus());
        activity.setDuration(request.getDuration());
        activity.setActivityDate(request.getActivityDate());

        DailyActivity savedActivity = dailyActivityRepository.save(activity);

        return convertToResponseDTO(savedActivity);
    }

    // Get All Activities
    public List<DailyActivityResponseDTO> getAllActivities() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return dailyActivityRepository.findByUser(user)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get Activity By Id
    public DailyActivityResponseDTO getActivityById(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DailyActivity activity = dailyActivityRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        return convertToResponseDTO(activity);
    }

    // Update Activity
    public DailyActivityResponseDTO updateActivity(Long id,
                                                   DailyActivityRequestDTO request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DailyActivity activity = dailyActivityRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        activity.setTitle(request.getTitle());
        activity.setDescription(request.getDescription());
        activity.setCategory(request.getCategory());
        activity.setStatus(request.getStatus());
        activity.setDuration(request.getDuration());
        activity.setActivityDate(request.getActivityDate());

        DailyActivity updatedActivity = dailyActivityRepository.save(activity);

        return convertToResponseDTO(updatedActivity);
    }

    // Delete Activity
    public void deleteActivity(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DailyActivity activity = dailyActivityRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        dailyActivityRepository.delete(activity);
    }

    // Convert Entity to DTO
    private DailyActivityResponseDTO convertToResponseDTO(DailyActivity activity) {

        DailyActivityResponseDTO response = new DailyActivityResponseDTO();

        response.setId(activity.getId());
        response.setTitle(activity.getTitle());
        response.setDescription(activity.getDescription());
        response.setCategory(activity.getCategory());
        response.setStatus(activity.getStatus());
        response.setDuration(activity.getDuration());
        response.setActivityDate(activity.getActivityDate());

        return response;
    }
}