package com.darshan.dailyprogress.dto;

public class DashboardResponseDTO {

    // Goals
    private Long totalGoals;
    private Long completedGoals;

    // Activities
    private Long totalActivities;

    // Habits
    private Long totalHabits;
    private Long activeHabits;

    // Planner
    private Long totalPlannerTasks;
    private Long completedPlannerTasks;
    private Long pendingPlannerTasks;

    public DashboardResponseDTO() {
    }

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

    public Long getTotalActivities() {
        return totalActivities;
    }

    public void setTotalActivities(Long totalActivities) {
        this.totalActivities = totalActivities;
    }

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
}