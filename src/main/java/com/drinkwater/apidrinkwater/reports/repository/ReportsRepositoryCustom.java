package com.drinkwater.apidrinkwater.reports.repository;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReportsRepositoryCustom {

    List<WaterIntakeReportDTO> findReport(Long userId, LocalDate startDate, LocalDate endDate);
    // Adicione outros métodos conforme necessário
}
