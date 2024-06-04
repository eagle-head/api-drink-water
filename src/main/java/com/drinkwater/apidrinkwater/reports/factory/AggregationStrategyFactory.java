package com.drinkwater.apidrinkwater.reports.factory;

import com.drinkwater.apidrinkwater.reports.model.Granularity;
import com.drinkwater.apidrinkwater.reports.strategy.AggregationStrategy;

public interface AggregationStrategyFactory {

    AggregationStrategy createStrategy(Granularity granularity);
}
