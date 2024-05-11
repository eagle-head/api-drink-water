package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.model.VolumeUnit;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class WaterIntakeResponseDTO {

    private Long id;
    private OffsetDateTime dateTimeUTC;
    private double volume;
    private VolumeUnit volumeUnit;
    private Long userId;
}
