package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.WeeklyProgressDayDTO;
import com.darshan.dailyprogress.entity.ActivityStatus;
import com.darshan.dailyprogress.entity.PlannerStatus;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.exception.ResourceNotFoundException;
import com.darshan.dailyprogress.repository.DailyActivityRepository;
import com.darshan.dailyprogress.repository.PlannerTaskRepository;
import com.darshan.dailyprogress.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeeklyProgressService {

    private final UserRepository userRepository;
    private final DailyActivityRepository dailyActivityRepository;
    private final PlannerTaskRepository plannerTaskRepository;

    public WeeklyProgressService(
            UserRepository userRepository,
            DailyActivityRepository dailyActivityRepository,
            PlannerTaskRepository plannerTaskRepository) {

        this.userRepository = userRepository;
        this.dailyActivityRepository = dailyActivityRepository;
        this.plannerTaskRepository = plannerTaskRepository;
    }

    public List<WeeklyProgressDayDTO> getWeeklyProgress() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        LocalDate today = LocalDate.now();

        List<WeeklyProgressDayDTO> weeklyProgress =
                new ArrayList<>();

        for (int i = 6; i >= 0; i--) {

            LocalDate date = today.minusDays(i);

            long totalActivities =
                    dailyActivityRepository
                            .countByUserAndActivityDateBetween(
                                    user,
                                    date,
                                    date);

            long completedActivities =
                    dailyActivityRepository
                            .countByUserAndStatusAndActivityDateBetween(
                                    user,
                                    ActivityStatus.COMPLETED,
                                    date,
                                    date);

            long totalPlannerTasks =
                    plannerTaskRepository
                            .countByUserAndDueDate(
                                    user,
                                    date);

            long completedPlannerTasks =
                    plannerTaskRepository
                            .countByUserAndStatusAndDueDate(
                                    user,
                                    PlannerStatus.COMPLETED,
                                    date);

            long totalItems =
                    totalActivities + totalPlannerTasks;

            long completedItems =
                    completedActivities + completedPlannerTasks;

            double progressPercentage = 0.0;

            if (totalItems > 0) {
                progressPercentage =
                        ((double) completedItems / totalItems) * 100.0;
            }

            progressPercentage =
                    Math.round(progressPercentage * 100.0) / 100.0;

            WeeklyProgressDayDTO dayDTO =
                    new WeeklyProgressDayDTO(
                            date,
                            totalItems,
                            completedItems,
                            progressPercentage);

            weeklyProgress.add(dayDTO);
        }

        return weeklyProgress;
    }
}