package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.model.VolumeUnit;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.OffsetDateTime;

@Data
public class WaterIntakeCreateDTO {

    @NotNull(message = "User ID is required.")
    private Long userId;

    @Positive(message = "Volume must be a positive number.")
    private int volume;

    @PastOrPresent(message = "Date and time of intake must be in the past or present.")
    private OffsetDateTime dateTimeUTC;

    @NotNull(message = "Volume unit is required.")
    private VolumeUnit volumeUnit;
}
