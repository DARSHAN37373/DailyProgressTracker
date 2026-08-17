package com.darshan.dailyprogress.dto;

import java.time.LocalDate;

public class WeeklyProgressDayDTO {

    private LocalDate date;

    private Long totalItems;

    private Long completedItems;

    private Double progressPercentage;

    public WeeklyProgressDayDTO() {
    }

    public WeeklyProgressDayDTO(
            LocalDate date,
            Long totalItems,
            Long completedItems,
            Double progressPercentage) {

        this.date = date;
        this.totalItems = totalItems;
        this.completedItems = completedItems;
        this.progressPercentage = progressPercentage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Long getCompletedItems() {
        return completedItems;
    }

    public void setCompletedItems(Long completedItems) {
        this.completedItems = completedItems;
    }

    public Double getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(Double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }
}