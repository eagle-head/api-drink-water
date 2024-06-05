package com.drinkwater.apidrinkwater.reports.controller;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.model.Granularity;
import com.drinkwater.apidrinkwater.reports.service.ReportsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/reports")
public class ReportsController {

    private final ReportsService reportService;

    public ReportsController(ReportsService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/consumption")
    public List<WaterIntakeReportDTO> findConsumptionReport(
        @PathVariable Long userId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
        @RequestParam String granularity) {

        Granularity granularityEnum = Granularity.fromValue(granularity);

        return this.reportService.getReport(userId, startDate, endDate, granularityEnum);
    }
}
