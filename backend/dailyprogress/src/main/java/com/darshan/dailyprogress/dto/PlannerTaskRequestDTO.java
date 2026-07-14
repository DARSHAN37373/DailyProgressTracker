package com.darshan.dailyprogress.dto;

import com.darshan.dailyprogress.entity.PlannerCategory;
import com.darshan.dailyprogress.entity.PlannerPriority;
import com.darshan.dailyprogress.entity.PlannerStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PlannerTaskRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Category is required")
    private PlannerCategory category;

    @NotNull(message = "Priority is required")
    private PlannerPriority priority;

    @NotNull(message = "Status is required")
    private PlannerStatus status;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    private LocalDateTime reminderTime;

    private Integer estimatedHours;

    private Boolean recurring;

    public PlannerTaskRequestDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlannerCategory getCategory() {
        return category;
    }

    public void setCategory(PlannerCategory category) {
        this.category = category;
    }

    public PlannerPriority getPriority() {
        return priority;
    }

    public void setPriority(PlannerPriority priority) {
        this.priority = priority;
    }

    public PlannerStatus getStatus() {
        return status;
    }

    public void setStatus(PlannerStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    // Generate getters and setters
}