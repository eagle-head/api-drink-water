package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.model.VolumeUnit;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class WaterIntakeResponseDTO {

    private UUID code;
    private Long userId;
    private OffsetDateTime dateTimeUTC;
    private int volume;
    private VolumeUnit volumeUnit;
}
