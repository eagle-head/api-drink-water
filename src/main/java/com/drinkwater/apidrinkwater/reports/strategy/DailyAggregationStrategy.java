package com.drinkwater.apidrinkwater.reports.strategy;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.repository.ReportsRepositoryCustom;

import java.time.LocalDate;
import java.util.List;

public class DailyAggregationStrategy extends AbstractAggregationStrategy {

    public DailyAggregationStrategy(ReportsRepositoryCustom reportsRepository) {
        super(reportsRepository);
    }

    @Override
    protected void validateDates(LocalDate startDate, LocalDate endDate) {
        if (!startDate.isEqual(endDate)) {
            // TODO: criar tratamento de erro no GlobalExceptionHandler
            throw new IllegalArgumentException("Start date and end date must be the same for daily aggregation.");
        }
    }

    @Override
    protected List<WaterIntakeReportDTO> fetchData(Long userId, LocalDate startDate, LocalDate endDate) {
        return reportsRepository.findReport(userId, startDate, endDate);
    }
}
