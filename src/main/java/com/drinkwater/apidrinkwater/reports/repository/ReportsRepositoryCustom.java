package com.drinkwater.apidrinkwater.reports.repository;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import java.time.OffsetDateTime;
import java.util.List;

public interface ReportsRepositoryCustom {

    List<WaterIntakeReportDTO> findReport(Long userId, OffsetDateTime startDate, OffsetDateTime endDate);
    // Adicione outros métodos conforme necessário
}
