package com.drinkwater.apidrinkwater.usermanagement.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsCreateDTO;
import com.drinkwater.apidrinkwater.usermanagement.model.BiologicalSex;
import com.drinkwater.apidrinkwater.usermanagement.model.HeightUnit;
import com.drinkwater.apidrinkwater.usermanagement.model.WeightUnit;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserCreateDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Past
    private OffsetDateTime birthDate;

    @NotNull
    private BiologicalSex biologicalSex;

    @Min(45)
    private double weight;

    @NotNull
    private WeightUnit weightUnit;

    @Min(100)
    private double height;

    @NotNull
    private HeightUnit heightUnit;

    @NotNull
    @Valid
    private AlarmSettingsCreateDTO alarmSettings;
}
