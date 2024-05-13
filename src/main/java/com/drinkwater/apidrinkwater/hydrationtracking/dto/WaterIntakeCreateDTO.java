package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.model.VolumeUnit;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.OffsetDateTime;

@Data
public class WaterIntakeCreateDTO {

    @NotNull
    private Long userId;

    @Positive
    private int volume;

    @NotNull
    @PastOrPresent
    private OffsetDateTime dateTimeUTC;

    @NotNull
    private VolumeUnit volumeUnit;
}
