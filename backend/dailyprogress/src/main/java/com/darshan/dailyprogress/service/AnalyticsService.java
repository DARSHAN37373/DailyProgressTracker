package com.darshan.dailyprogress.service;

import com.darshan.dailyprogress.dto.AnalyticsResponseDTO;
import com.darshan.dailyprogress.entity.GoalStatus;
import com.darshan.dailyprogress.entity.HabitStatus;
import com.darshan.dailyprogress.entity.PlannerStatus;
import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.repository.DailyActivityRepository;
import com.darshan.dailyprogress.repository.GoalRepository;
import com.darshan.dailyprogress.repository.HabitRepository;
import com.darshan.dailyprogress.repository.PlannerTaskRepository;
import com.darshan.dailyprogress.repository.UserRepository;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.darshan.dailyprogress.entity.DailyActivity;
import java.util.List;

@Service
public class AnalyticsService {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final HabitRepository habitRepository;
    private final PlannerTaskRepository plannerTaskRepository;
    private final DailyActivityRepository dailyActivityRepository;

    public AnalyticsService(UserRepository userRepository,
                            GoalRepository goalRepository,
                            HabitRepository habitRepository,
                            PlannerTaskRepository plannerTaskRepository,
                            DailyActivityRepository dailyActivityRepository) {

        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.habitRepository = habitRepository;
        this.plannerTaskRepository = plannerTaskRepository;
        this.dailyActivityRepository = dailyActivityRepository;
    }

    public AnalyticsResponseDTO getAnalytics() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AnalyticsResponseDTO response = new AnalyticsResponseDTO();

        // Goal Completion %
        long totalGoals = goalRepository.countByUser(user);
        long completedGoals = goalRepository.countByUserAndStatus(user, GoalStatus.COMPLETED);

        double goalRate = totalGoals == 0
                ? 0
                : (completedGoals * 100.0) / totalGoals;

        response.setGoalCompletionRate(goalRate);

        // Habit Completion %
        long totalHabits = habitRepository.countByUser(user);
        long activeHabits = habitRepository.countByUserAndStatus(user, HabitStatus.ACTIVE);

        double habitRate = totalHabits == 0
                ? 0
                : (activeHabits * 100.0) / totalHabits;

        response.setHabitCompletionRate(habitRate);

        // Planner Completion %
        long totalPlanner = plannerTaskRepository.countByUser(user);
        long completedPlanner = plannerTaskRepository.countByUserAndStatus(user, PlannerStatus.COMPLETED);

        double plannerRate = totalPlanner == 0
                ? 0
                : (completedPlanner * 100.0) / totalPlanner;

        response.setPlannerCompletionRate(plannerRate);

        // Simple Productivity Score
        double productivityScore =
                (goalRate + habitRate + plannerRate) / 3.0;

        response.setProductivityScore(productivityScore);

        // Placeholder values (we'll implement these next)
        LocalDate today = LocalDate.now();

// Weekly
LocalDate weekStart = today.minusDays(6);

long weeklyCount =
        dailyActivityRepository.countByUserAndActivityDateBetween(
                user,
                weekStart,
                today);

response.setWeeklyActivityCount(weeklyCount);

// Monthly
LocalDate monthStart = today.withDayOfMonth(1);

long monthlyCount =
        dailyActivityRepository.countByUserAndActivityDateBetween(
                user,
                monthStart,
                today);

response.setMonthlyActivityCount(monthlyCount);

// Activity Duration Analytics
List<DailyActivity> activities = dailyActivityRepository.findByUser(user);

int studyMinutes = 0;
int workoutMinutes = 0;
int projectMinutes = 0;
int readingMinutes = 0;

for (DailyActivity activity : activities) {

    if (activity.getDuration() == null) {
        continue;
    }

    switch (activity.getCategory()) {

        case STUDY:
            studyMinutes += activity.getDuration();
            break;

        case WORKOUT:
            workoutMinutes += activity.getDuration();
            break;

        case PROJECT:
            projectMinutes += activity.getDuration();
            break;

        case READING:
            readingMinutes += activity.getDuration();
            break;

        default:
            break;
    }
}

response.setTotalStudyMinutes(studyMinutes);
response.setTotalWorkoutMinutes(workoutMinutes);
response.setTotalProjectMinutes(projectMinutes);
response.setTotalReadingMinutes(readingMinutes);


// Current Streak & Longest Streak
List<DailyActivity> completedActivities =
        dailyActivityRepository.findByUserAndStatusOrderByActivityDateAsc(
                user,
                com.darshan.dailyprogress.entity.ActivityStatus.COMPLETED);

int currentStreak = 0;
int longestStreak = 0;

LocalDate previousDate = null;
int tempStreak = 0;

for (DailyActivity activity : completedActivities) {

    LocalDate currentDate = activity.getActivityDate();

    if (previousDate == null) {
        tempStreak = 1;
    } else if (currentDate.equals(previousDate.plusDays(1))) {
        tempStreak++;
    } else if (!currentDate.equals(previousDate)) {
        tempStreak = 1;
    }

    longestStreak = Math.max(longestStreak, tempStreak);
    previousDate = currentDate;
}

// Calculate current streak ending today (or yesterday)
LocalDate expectedDate = LocalDate.now();

for (int i = completedActivities.size() - 1; i >= 0; i--) {

    LocalDate date = completedActivities.get(i).getActivityDate();

    if (date.equals(expectedDate)) {
        currentStreak++;
        expectedDate = expectedDate.minusDays(1);
    } else if (date.equals(expectedDate.minusDays(1)) && currentStreak == 0) {
        currentStreak++;
        expectedDate = date.minusDays(1);
    } else if (!date.equals(expectedDate)) {
        break;
    }
}

response.setCurrentStreak(currentStreak);
response.setLongestStreak(longestStreak);
        

return response;
    }
}