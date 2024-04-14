package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AlarmSettingsCreateDTO {

    @Min(value = 15, message = "Interval must be at least 15 minutes")
    private int intervalMinutes;

    @NotNull(message = "Start time is required")
    private Date startTime;

    @NotNull(message = "End time is required")
    private Date endTime;
}
