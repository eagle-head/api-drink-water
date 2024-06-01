package com.drinkwater.apidrinkwater.reports.controller;

import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeRequestDTO;
import com.drinkwater.apidrinkwater.reports.service.ReportsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    private final ReportsService reportsService;

    @Autowired
    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping("/daily")
    public List<DailyWaterIntakeReportDTO> findDailyReport(@Valid @RequestBody DailyWaterIntakeRequestDTO request) {
        // TODO: mudar nome do metodo
        return this.reportsService.getDailyReport(request);
    }
}
