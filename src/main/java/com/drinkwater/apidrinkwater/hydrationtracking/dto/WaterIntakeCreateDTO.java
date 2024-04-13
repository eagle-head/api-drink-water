package com.drinkwater.apidrinkwater.hydrationtracking.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.model.VolumeUnit;
import lombok.Data;

import java.util.Date;

@Data
public class WaterIntakeCreateDTO {

    private Long userId;
    private double volume;
    private Date dateTimeUTC;
    private VolumeUnit volumeUnit;
}
