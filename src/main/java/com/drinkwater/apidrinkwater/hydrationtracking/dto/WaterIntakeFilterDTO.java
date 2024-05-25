package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.model.VolumeUnit;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class WaterIntakeFilterDTO {

    private OffsetDateTime startDateTimeUTC;
    private OffsetDateTime endDateTimeUTC;
    private Integer minVolume;
    private Integer maxVolume;
    private VolumeUnit volumeUnit;
}
