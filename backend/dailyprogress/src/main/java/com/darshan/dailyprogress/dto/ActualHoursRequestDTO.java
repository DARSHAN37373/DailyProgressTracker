package com.darshan.dailyprogress.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ActualHoursRequestDTO {

    @NotNull(message = "Actual hours is required")
    @PositiveOrZero(message = "Actual hours cannot be negative")
    private Integer actualHours;

    public ActualHoursRequestDTO() {
    }

    public Integer getActualHours() {
        return actualHours;
    }

    public void setActualHours(Integer actualHours) {
        this.actualHours = actualHours;
    }
}