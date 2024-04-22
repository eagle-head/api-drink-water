package com.drinkwater.apidrinkwater.usermanagement.dto;

import com.drinkwater.apidrinkwater.usermanagement.model.BiologicalSex;
import com.drinkwater.apidrinkwater.usermanagement.model.HeightUnit;
import com.drinkwater.apidrinkwater.usermanagement.model.WeightUnit;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Date;

@Data
public class UserUpdateDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 characters")
    private String firstName;

    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters")
    private String lastName;

    @Past(message = "Birth date must be in the past")
    private Date birthDate;

    private BiologicalSex biologicalSex;

    @Positive(message = "Weight must be a positive value")
    private Double weight;

    private WeightUnit weightUnit;

    @Positive(message = "Height must be a positive value")
    private Double height;

    private HeightUnit heightUnit;
}
