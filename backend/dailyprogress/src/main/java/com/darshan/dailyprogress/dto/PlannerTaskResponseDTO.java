package com.darshan.dailyprogress.dto;

import com.darshan.dailyprogress.entity.PlannerCategory;
import com.darshan.dailyprogress.entity.PlannerPriority;
import com.darshan.dailyprogress.entity.PlannerStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PlannerTaskResponseDTO {

    private Long id;

    private String title;

    private String description;

    private PlannerCategory category;

    private PlannerPriority priority;

    private PlannerStatus status;

    private LocalDate startDate;

    private LocalDate dueDate;

    private LocalDateTime reminderTime;

    private Integer estimatedHours;

    private Integer actualHours;

    private Boolean recurring;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public PlannerTaskResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getActualHours() {
        return actualHours;
    }

    public void setActualHours(Integer actualHours) {
        this.actualHours = actualHours;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Generate getters and setters
}