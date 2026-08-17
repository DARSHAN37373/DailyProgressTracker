package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.WeeklyProgressDayDTO;
import com.darshan.dailyprogress.service.WeeklyProgressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard/weekly")
public class WeeklyProgressController {

    private final WeeklyProgressService weeklyProgressService;

    public WeeklyProgressController(
            WeeklyProgressService weeklyProgressService) {
        this.weeklyProgressService = weeklyProgressService;
    }

    @GetMapping
    public List<WeeklyProgressDayDTO> getWeeklyProgress() {
        return weeklyProgressService.getWeeklyProgress();
    }
}