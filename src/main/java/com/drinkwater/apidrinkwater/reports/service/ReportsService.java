package com.drinkwater.apidrinkwater.reports.service;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.factory.AggregationStrategyFactory;
import com.drinkwater.apidrinkwater.reports.model.Granularity;
import com.drinkwater.apidrinkwater.reports.strategy.AggregationStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportsService {

    private final AggregationStrategyFactory strategyFactory;

    public ReportsService(AggregationStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public List<WaterIntakeReportDTO> getReport(Long userId,
                                                LocalDate startDate,
                                                LocalDate endDate,
                                                Granularity granularity) {

        AggregationStrategy strategy = this.strategyFactory.createStrategy(granularity);

        return strategy.aggregateData(userId, startDate, endDate);
    }
}
