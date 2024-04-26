package com.drinkwater.apidrinkwater.usermanagement.dto;

import com.drinkwater.apidrinkwater.usermanagement.model.BiologicalSex;
import com.drinkwater.apidrinkwater.usermanagement.model.HeightUnit;
import com.drinkwater.apidrinkwater.usermanagement.model.WeightUnit;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Date;

@Data
public class UserUpdateDTO {

    @Email
    private String email;

    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String firstName;

    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    @Past(message = "Birth date must be in the past")
    private Date birthDate;

    private BiologicalSex biologicalSex;

    @Min(value = 45, message = "Weight must be at least 45")
    private Double weight;

    private WeightUnit weightUnit;

    @Min(value = 100, message = "Height must be at least 100")
    private Double height;

    private HeightUnit heightUnit;
}
