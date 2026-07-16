package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.DashboardResponseDTO;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.darshan.dailyprogress.entity.GoalStatus;
import com.darshan.dailyprogress.entity.HabitStatus;
import com.darshan.dailyprogress.entity.PlannerStatus;

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
                 goalRepository.countByUser(user));


        response.setCompletedGoals(
        goalRepository.countByUserAndStatus(
                user,
                GoalStatus.COMPLETED));

        response.setTotalActivities(
                dailyActivityRepository.countByUser(user));

        response.setTotalHabits(
                habitRepository.countByUser(user));

        response.setActiveHabits(
        habitRepository.countByUserAndStatus(
                user,
                HabitStatus.ACTIVE));

        response.setTotalPlannerTasks(
                plannerTaskRepository.countByUser(user));

        response.setCompletedPlannerTasks(
        plannerTaskRepository.countByUserAndStatus(
                user,
                PlannerStatus.COMPLETED));

        response.setPendingPlannerTasks(
    plannerTaskRepository.countByUserAndStatus(
            user,
            PlannerStatus.PLANNED));

        return response;
    }
}