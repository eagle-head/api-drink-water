package com.drinkwater.apidrinkwater.reports.strategy;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.repository.ReportsRepositoryCustom;

import java.time.OffsetDateTime;
import java.util.List;

public class DailyAggregationStrategy extends AbstractAggregationStrategy {

    public DailyAggregationStrategy(ReportsRepositoryCustom reportsRepository) {
        super(reportsRepository);
    }

    @Override
    protected void validateDates(OffsetDateTime startDate, OffsetDateTime endDate) {
        // Validação específica para agregação diária
    }

    @Override
    protected List<WaterIntakeReportDTO> fetchData(Long userId, OffsetDateTime startDate, OffsetDateTime endDate) {
        return reportsRepository.findReport(userId, startDate, endDate);
    }
}
