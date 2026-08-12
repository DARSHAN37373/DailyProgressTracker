package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.DashboardResponseDTO;
import com.darshan.dailyprogress.entity.ActivityStatus;
import com.darshan.dailyprogress.entity.GoalStatus;
import com.darshan.dailyprogress.entity.HabitStatus;
import com.darshan.dailyprogress.entity.PlannerStatus;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.exception.ResourceNotFoundException;
import com.darshan.dailyprogress.repository.DailyActivityRepository;
import com.darshan.dailyprogress.repository.GoalRepository;
import com.darshan.dailyprogress.repository.HabitRepository;
import com.darshan.dailyprogress.repository.PlannerTaskRepository;
import com.darshan.dailyprogress.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final DailyActivityRepository dailyActivityRepository;
    private final HabitRepository habitRepository;
    private final PlannerTaskRepository plannerTaskRepository;

    public DashboardService(
            UserRepository userRepository,
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
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        DashboardResponseDTO response = new DashboardResponseDTO();

        LocalDate today = LocalDate.now();

        // =========================
        // Goals
        // =========================

        response.setTotalGoals(
                goalRepository.countByUser(user));

        response.setCompletedGoals(
                goalRepository.countByUserAndStatus(
                        user,
                        GoalStatus.COMPLETED));

        // =========================
        // Activities
        // =========================

        response.setTotalActivities(
                dailyActivityRepository.countByUser(user));

        long todayActivities =
                dailyActivityRepository.countByUserAndActivityDateBetween(
                        user,
                        today,
                        today);

        long todayCompletedActivities =
                dailyActivityRepository.countByUserAndStatusAndActivityDateBetween(
                        user,
                        ActivityStatus.COMPLETED,
                        today,
                        today);

        response.setTodayActivities(todayActivities);

        response.setTodayCompletedActivities(
                todayCompletedActivities);

        // =========================
        // Habits
        // =========================

        response.setTotalHabits(
                habitRepository.countByUser(user));

        response.setActiveHabits(
                habitRepository.countByUserAndStatus(
                        user,
                        HabitStatus.ACTIVE));

        // =========================
        // Planner Tasks
        // =========================

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

        response.setTotalEstimatedHours(
                plannerTaskRepository.sumEstimatedHoursByUser(user));

        response.setTotalActualHours(
                plannerTaskRepository.sumActualHoursByUser(user));

        // Today's Planner Tasks

        long todayPlannerTasks =
                plannerTaskRepository.countByUserAndDueDate(
                        user,
                        today);

        long todayCompletedPlannerTasks =
                plannerTaskRepository.countByUserAndStatusAndDueDate(
                        user,
                        PlannerStatus.COMPLETED,
                        today);

        response.setTodayPlannerTasks(
                todayPlannerTasks);

        response.setTodayCompletedPlannerTasks(
                todayCompletedPlannerTasks);

       // =========================
// Today's Progress
// =========================

long totalTodayItems =
        todayActivities + todayPlannerTasks;

long completedTodayItems =
        todayCompletedActivities + todayCompletedPlannerTasks;

double progressPercentage = 0.0;

if (totalTodayItems > 0) {
    progressPercentage =
            ((double) completedTodayItems / totalTodayItems) * 100.0;
}

if (progressPercentage < 0) {
    progressPercentage = 0.0;
}

if (progressPercentage > 100) {
    progressPercentage = 100.0;
}

response.setTodayProgressPercentage(
        Math.round(progressPercentage * 100.0) / 100.0);

        return response;
    }
}