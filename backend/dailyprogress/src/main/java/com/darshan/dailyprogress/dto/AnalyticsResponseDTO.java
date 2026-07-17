package com.darshan.dailyprogress.dto;

public class AnalyticsResponseDTO {

    private double productivityScore;

    private double goalCompletionRate;
    private double habitCompletionRate;
    private double plannerCompletionRate;

    private long weeklyActivityCount;
    private long monthlyActivityCount;

    private int totalStudyMinutes;
    private int totalWorkoutMinutes;
    private int totalProjectMinutes;
    private int totalReadingMinutes;

    private int currentStreak;
    private int longestStreak;

    public AnalyticsResponseDTO() {
    }

    public double getProductivityScore() {
        return productivityScore;
    }

    public void setProductivityScore(double productivityScore) {
        this.productivityScore = productivityScore;
    }

    public double getGoalCompletionRate() {
        return goalCompletionRate;
    }

    public void setGoalCompletionRate(double goalCompletionRate) {
        this.goalCompletionRate = goalCompletionRate;
    }

    public double getHabitCompletionRate() {
        return habitCompletionRate;
    }

    public void setHabitCompletionRate(double habitCompletionRate) {
        this.habitCompletionRate = habitCompletionRate;
    }

    public double getPlannerCompletionRate() {
        return plannerCompletionRate;
    }

    public void setPlannerCompletionRate(double plannerCompletionRate) {
        this.plannerCompletionRate = plannerCompletionRate;
    }

    public long getWeeklyActivityCount() {
        return weeklyActivityCount;
    }

    public void setWeeklyActivityCount(long weeklyActivityCount) {
        this.weeklyActivityCount = weeklyActivityCount;
    }

    public long getMonthlyActivityCount() {
        return monthlyActivityCount;
    }

    public void setMonthlyActivityCount(long monthlyActivityCount) {
        this.monthlyActivityCount = monthlyActivityCount;
    }

    public int getTotalStudyMinutes() {
        return totalStudyMinutes;
    }

    public void setTotalStudyMinutes(int totalStudyMinutes) {
        this.totalStudyMinutes = totalStudyMinutes;
    }

    public int getTotalWorkoutMinutes() {
        return totalWorkoutMinutes;
    }

    public void setTotalWorkoutMinutes(int totalWorkoutMinutes) {
        this.totalWorkoutMinutes = totalWorkoutMinutes;
    }

    public int getTotalProjectMinutes() {
        return totalProjectMinutes;
    }

    public void setTotalProjectMinutes(int totalProjectMinutes) {
        this.totalProjectMinutes = totalProjectMinutes;
    }

    public int getTotalReadingMinutes() {
        return totalReadingMinutes;
    }

   
public void setTotalReadingMinutes(int totalReadingMinutes) {
    this.totalReadingMinutes = totalReadingMinutes;
}

public int getCurrentStreak() {
    return currentStreak;
}

public void setCurrentStreak(int currentStreak) {
    this.currentStreak = currentStreak;
}

public int getLongestStreak() {
    return longestStreak;
}

public void setLongestStreak(int longestStreak) {
    this.longestStreak = longestStreak;
}
    }
