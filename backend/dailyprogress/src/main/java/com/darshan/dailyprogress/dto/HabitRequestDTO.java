package com.darshan.dailyprogress.dto;

import com.darshan.dailyprogress.entity.HabitFrequency;
import com.darshan.dailyprogress.entity.HabitStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HabitRequestDTO {

    @NotBlank(message = "Habit name is required")
    private String name;

    private String description;

    @NotNull(message = "Frequency is required")
    private HabitFrequency frequency;

    @NotNull(message = "Status is required")
    private HabitStatus status;

    @NotNull(message = "Target count is required")
    private Integer targetCount;

    public HabitRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HabitFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(HabitFrequency frequency) {
        this.frequency = frequency;
    }

    public HabitStatus getStatus() {
        return status;
    }

    public void setStatus(HabitStatus status) {
        this.status = status;
    }

    public Integer getTargetCount() {
        return targetCount;
    }

    public void setTargetCount(Integer targetCount) {
        this.targetCount = targetCount;
    }
}