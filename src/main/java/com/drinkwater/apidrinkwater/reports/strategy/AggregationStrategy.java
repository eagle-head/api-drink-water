package com.drinkwater.apidrinkwater.reports.strategy;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import java.time.OffsetDateTime;
import java.util.List;

public interface AggregationStrategy {

    List<WaterIntakeReportDTO> aggregateData(Long userId, OffsetDateTime startDate, OffsetDateTime endDate);
}
