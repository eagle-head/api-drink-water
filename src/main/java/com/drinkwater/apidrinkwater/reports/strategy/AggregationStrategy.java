package com.drinkwater.apidrinkwater.reports.strategy;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;

import java.time.LocalDate;
import java.util.List;

public interface AggregationStrategy {

    List<WaterIntakeReportDTO> aggregateData(Long userId, LocalDate startDate, LocalDate endDate);
}
