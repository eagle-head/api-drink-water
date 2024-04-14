package com.drinkwater.apidrinkwater.usermanagement.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsCreateDTO;
import com.drinkwater.apidrinkwater.usermanagement.model.BiologicalSex;
import com.drinkwater.apidrinkwater.usermanagement.model.HeightUnit;
import com.drinkwater.apidrinkwater.usermanagement.model.WeightUnit;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class UserCreateDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private Date birthDate;

    @NotNull(message = "Biological sex is required")
    private BiologicalSex biologicalSex;

    @Min(value = 0, message = "Weight must be greater than zero")
    private double weight;

    @NotNull(message = "Weight unit is required")
    private WeightUnit weightUnit;

    @Min(value = 0, message = "Height must be greater than zero")
    private double height;

    @NotNull(message = "Height unit is required")
    private HeightUnit heightUnit;

    @NotNull(message = "Alarm settings are required")
    @Valid
    private AlarmSettingsCreateDTO alarmSettings;
}
