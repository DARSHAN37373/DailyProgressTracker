package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.DashboardResponseDTO;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final DailyActivityRepository dailyActivityRepository;
    private final HabitRepository habitRepository;
    private final PlannerTaskRepository plannerTaskRepository;

    public DashboardService(UserRepository userRepository,
                            GoalRepository goalRepository,
                            DailyActivityRepository dailyActivityRepository,
                            HabitRepository habitRepository,
                            PlannerTaskRepository plannerTaskRepository) {

        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.dailyActivityRepository = dailyActivityRepository;
        this.habitRepository = habitRepository;
        this.plannerTaskRepository = plannerTaskRepository;
    }

    public DashboardResponseDTO getDashboard() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DashboardResponseDTO response = new DashboardResponseDTO();

        response.setTotalGoals(
                (long) goalRepository.findByUser(user).size());

        response.setTotalActivities(
                (long) dailyActivityRepository.findByUser(user).size());

        response.setTotalHabits(
                (long) habitRepository.findByUser(user).size());

        response.setTotalPlannerTasks(
                (long) plannerTaskRepository.findByUser(user).size());

        return response;
    }
}