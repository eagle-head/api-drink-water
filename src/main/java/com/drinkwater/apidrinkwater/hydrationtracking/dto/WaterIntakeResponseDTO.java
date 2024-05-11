package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.model.VolumeUnit;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class WaterIntakeResponseDTO {

    private Long id;
    private OffsetDateTime dateTimeUTC;
    private int volume;
    private VolumeUnit volumeUnit;
    private Long userId;
}
