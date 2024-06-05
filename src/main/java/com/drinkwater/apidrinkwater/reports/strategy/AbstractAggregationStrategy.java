package com.drinkwater.apidrinkwater.reports.strategy;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.repository.ReportsRepositoryCustom;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractAggregationStrategy implements AggregationStrategy {

    protected final ReportsRepositoryCustom reportsRepository;

    public AbstractAggregationStrategy(ReportsRepositoryCustom reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    @Override
    public List<WaterIntakeReportDTO> aggregateData(Long userId,
                                                    LocalDate startDate,
                                                    LocalDate endDate) {
        this.validateDates(startDate, endDate);
        return this.fetchData(userId, startDate, endDate);
    }

    protected abstract void validateDates(LocalDate startDate, LocalDate endDate);

    protected abstract List<WaterIntakeReportDTO> fetchData(Long userId,
                                                            LocalDate startDate,
                                                            LocalDate endDate);
}
