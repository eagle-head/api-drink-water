package com.drinkwater.apidrinkwater.reports.strategy;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.repository.ReportsRepositoryCustom;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class WeeklyAggregationStrategy extends AbstractAggregationStrategy {

    public WeeklyAggregationStrategy(ReportsRepositoryCustom reportsRepository) {
        super(reportsRepository);
    }

    @Override
    protected void validateDates(LocalDate startDate, LocalDate endDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (daysBetween != 7) {
            // TODO: criar tratamento de erro no GlobalExceptionHandler
            throw new IllegalArgumentException("The interval between start date and end date must be exactly one week (7 days) for weekly aggregation.");
        }
    }

    @Override
    protected List<WaterIntakeReportDTO> fetchData(Long userId, LocalDate startDate, LocalDate endDate) {
        return reportsRepository.findReport(userId, startDate, endDate);
    }
}
