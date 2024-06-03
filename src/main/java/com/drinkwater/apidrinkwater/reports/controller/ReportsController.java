package com.drinkwater.apidrinkwater.reports.controller;

import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.service.ReportsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("users/{userId}/reports")
public class ReportsController {

    private final ReportsService reportsService;

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping("/daily")
    public List<DailyWaterIntakeReportDTO> findDailyReport(
        @PathVariable Long userId,
        @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date) {

        return this.reportsService.findDailyReport(userId, date);
    }
}
