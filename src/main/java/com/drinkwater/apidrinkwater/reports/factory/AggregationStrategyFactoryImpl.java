package com.drinkwater.apidrinkwater.reports.factory;

import com.drinkwater.apidrinkwater.reports.model.Granularity;
import com.drinkwater.apidrinkwater.reports.strategy.AggregationStrategy;
import com.drinkwater.apidrinkwater.reports.strategy.DailyAggregationStrategy;
import com.drinkwater.apidrinkwater.reports.repository.ReportsRepositoryCustom;
import com.drinkwater.apidrinkwater.reports.strategy.WeeklyAggregationStrategy;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class AggregationStrategyFactoryImpl implements AggregationStrategyFactory {

    private final Map<Granularity, AggregationStrategy> strategies = new EnumMap<>(Granularity.class);

    public AggregationStrategyFactoryImpl(ReportsRepositoryCustom reportsRepository) {
        this.strategies.put(Granularity.DAILY, new DailyAggregationStrategy(reportsRepository));
        this.strategies.put(Granularity.WEEKLY, new WeeklyAggregationStrategy(reportsRepository));
    }

    @Override
    public AggregationStrategy createStrategy(Granularity granularity) {
        return this.strategies.get(granularity);
    }
}
