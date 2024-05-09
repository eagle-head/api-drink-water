package com.drinkwater.apidrinkwater.usermanagement.dto;

import com.drinkwater.apidrinkwater.usermanagement.model.BiologicalSex;
import com.drinkwater.apidrinkwater.usermanagement.model.HeightUnit;
import com.drinkwater.apidrinkwater.usermanagement.model.WeightUnit;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserUpdateDTO {

    @Email
    private String email;

    @Size(min = 6, max = 20)
    private String password;

    @Size(min = 1, max = 50)
    private String firstName;

    @Size(min = 1, max = 50)
    private String lastName;

    @Past
    private OffsetDateTime birthDate;

    private BiologicalSex biologicalSex;

    @Min(value = 45)
    private Double weight;

    private WeightUnit weightUnit;

    @Min(value = 100)
    private Double height;

    private HeightUnit heightUnit;
}
