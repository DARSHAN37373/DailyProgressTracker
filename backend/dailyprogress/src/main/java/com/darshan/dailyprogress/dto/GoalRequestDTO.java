package com.darshan.dailyprogress.dto;

import com.darshan.dailyprogress.entity.GoalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class GoalRequestDTO {

   @NotBlank(message = "Title is required")
@Size(max = 100, message = "Title cannot exceed 100 characters")
private String title;

@NotBlank(message = "Description is required")
@Size(max = 500, message = "Description cannot exceed 500 characters")
private String description;

@NotNull(message = "Target date is required")
private LocalDate targetDate;

@NotNull(message = "Status is required")
private GoalStatus status;

    public GoalRequestDTO() {
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

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public GoalStatus getStatus() {
        return status;
    }

    public void setStatus(GoalStatus status) {
        this.status = status;
    }
}