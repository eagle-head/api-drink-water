package com.drinkwater.apidrinkwater.reports.repository;

import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeRequestDTO;

import java.util.List;

public interface ReportsRepositoryCustom {

    List<DailyWaterIntakeReportDTO> findDailyReport(DailyWaterIntakeRequestDTO request);
}
