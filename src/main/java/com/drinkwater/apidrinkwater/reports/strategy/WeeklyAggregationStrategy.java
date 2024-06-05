package com.drinkwater.apidrinkwater.reports.strategy;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.repository.ReportsRepositoryCustom;

import java.time.LocalDate;
import java.util.List;

public class WeeklyAggregationStrategy extends AbstractAggregationStrategy {

    public WeeklyAggregationStrategy(ReportsRepositoryCustom reportsRepository) {
        super(reportsRepository);
    }

    @Override
    protected void validateDates(LocalDate startDate, LocalDate endDate) {
        // Validação específica para agregação semanal
    }

    @Override
    protected List<WaterIntakeReportDTO> fetchData(Long userId, LocalDate startDate, LocalDate endDate) {
        return reportsRepository.findReport(userId, startDate, endDate);
    }
}
