package com.drinkwater.apidrinkwater.reports.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Getter
@Setter
public class DailyWaterIntakeRequestDTO {

    // TODO: criar a mensagens de erro
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date must be in the past or present")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime date;
}
