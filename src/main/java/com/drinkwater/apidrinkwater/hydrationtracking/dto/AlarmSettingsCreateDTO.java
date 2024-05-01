package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import com.drinkwater.apidrinkwater.validation.TimeRangeConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@TimeRangeConstraint
@Data
public class AlarmSettingsCreateDTO {

    @Min(15)
    private int intervalMinutes;

    @NotNull(message = "Start time is required")
    private Date startTime;

    @NotNull(message = "End time is required")
    private Date endTime;
}
