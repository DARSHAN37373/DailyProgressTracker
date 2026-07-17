package com.darshan.dailyprogress.controller;

import com.darshan.dailyprogress.dto.AnalyticsResponseDTO;
import com.darshan.dailyprogress.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping
    public AnalyticsResponseDTO getAnalytics() {
        return analyticsService.getAnalytics();
    }
}