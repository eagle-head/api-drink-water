package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AlarmSettingsResponseDTO {

    private int intervalMinutes;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
}
