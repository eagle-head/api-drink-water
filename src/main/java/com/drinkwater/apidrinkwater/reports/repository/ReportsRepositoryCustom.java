package com.drinkwater.apidrinkwater.reports.repository;

import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeReportDTO;

import java.time.OffsetDateTime;
import java.util.List;

public interface ReportsRepositoryCustom {

    List<DailyWaterIntakeReportDTO> findDailyReport(Long userId, OffsetDateTime date);
}
