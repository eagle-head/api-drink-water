package com.drinkwater.apidrinkwater.reports.service;

import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.reports.repository.ReportsRepositoryCustom;
import com.drinkwater.apidrinkwater.usermanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ReportsService {

    private final ReportsRepositoryCustom reportsRepositoryCustom;
    private final UserService userService;

    public ReportsService(ReportsRepositoryCustom reportsRepositoryCustom, UserService userService) {
        this.reportsRepositoryCustom = reportsRepositoryCustom;
        this.userService = userService;
    }

    public List<DailyWaterIntakeReportDTO> findDailyReport(Long userId, OffsetDateTime date) {
        this.userService.existsById(userId);

        return this.reportsRepositoryCustom.findDailyReport(userId, date);
    }
}
