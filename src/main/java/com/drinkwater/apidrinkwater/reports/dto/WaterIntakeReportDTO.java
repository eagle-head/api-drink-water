package com.drinkwater.apidrinkwater.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class WaterIntakeReportDTO {

    private Date date;
    private long totalIntakes;
    private double totalVolume;
}
