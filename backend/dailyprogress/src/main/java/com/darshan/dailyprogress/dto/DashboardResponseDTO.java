package com.darshan.dailyprogress.dto;

public class DashboardResponseDTO {

    // Goals
    private Long totalGoals;
    private Long completedGoals;

    // Activities
    private Long totalActivities;
    private Long todayActivities;

    // Habits
    private Long totalHabits;
    private Long activeHabits;

    // Planner
    private Long totalPlannerTasks;
    private Long completedPlannerTasks;
    private Long pendingPlannerTasks;

    private Long totalEstimatedHours;
    private Long totalActualHours;

    private Long todayCompletedActivities;

    private Long todayPlannerTasks;
    private Long todayCompletedPlannerTasks;

    private Double todayProgressPercentage;

    public DashboardResponseDTO() {
    }

    // Goals

    public Long getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(Long totalGoals) {
        this.totalGoals = totalGoals;
    }

    public Long getCompletedGoals() {
        return completedGoals;
    }

    public void setCompletedGoals(Long completedGoals) {
        this.completedGoals = completedGoals;
    }

    // Activities

    public Long getTotalActivities() {
        return totalActivities;
    }

    public void setTotalActivities(Long totalActivities) {
        this.totalActivities = totalActivities;
    }

    public Long getTodayActivities() {
        return todayActivities;
    }

    public void setTodayActivities(Long todayActivities) {
        this.todayActivities = todayActivities;
    }

    // Habits

    public Long getTotalHabits() {
        return totalHabits;
    }

    public void setTotalHabits(Long totalHabits) {
        this.totalHabits = totalHabits;
    }

    public Long getActiveHabits() {
        return activeHabits;
    }

    public void setActiveHabits(Long activeHabits) {
        this.activeHabits = activeHabits;
    }

    // Planner

    public Long getTotalPlannerTasks() {
        return totalPlannerTasks;
    }

    public void setTotalPlannerTasks(Long totalPlannerTasks) {
        this.totalPlannerTasks = totalPlannerTasks;
    }

    public Long getCompletedPlannerTasks() {
        return completedPlannerTasks;
    }

    public void setCompletedPlannerTasks(Long completedPlannerTasks) {
        this.completedPlannerTasks = completedPlannerTasks;
    }

    public Long getPendingPlannerTasks() {
        return pendingPlannerTasks;
    }

    public void setPendingPlannerTasks(Long pendingPlannerTasks) {
        this.pendingPlannerTasks = pendingPlannerTasks;
    }
    public Long getTotalEstimatedHours() {
    return totalEstimatedHours;
}

public void setTotalEstimatedHours(Long totalEstimatedHours) {
    this.totalEstimatedHours = totalEstimatedHours;
}

public Long getTotalActualHours() {
    return totalActualHours;
}

public void setTotalActualHours(Long totalActualHours) {
    this.totalActualHours = totalActualHours;
}
public Long getTodayCompletedActivities() {
    return todayCompletedActivities;
}

public void setTodayCompletedActivities(Long todayCompletedActivities) {
    this.todayCompletedActivities = todayCompletedActivities;
}

public Long getTodayPlannerTasks() {
    return todayPlannerTasks;
}

public void setTodayPlannerTasks(Long todayPlannerTasks) {
    this.todayPlannerTasks = todayPlannerTasks;
}

public Long getTodayCompletedPlannerTasks() {
    return todayCompletedPlannerTasks;
}

public void setTodayCompletedPlannerTasks(Long todayCompletedPlannerTasks) {
    this.todayCompletedPlannerTasks = todayCompletedPlannerTasks;
}

public Double getTodayProgressPercentage() {
    return todayProgressPercentage;
}

public void setTodayProgressPercentage(Double todayProgressPercentage) {
    this.todayProgressPercentage = todayProgressPercentage;
}
}