package com.drinkwater.apidrinkwater.usermanagement.dto;

import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import com.drinkwater.apidrinkwater.usermanagement.model.BiologicalSex;
import com.drinkwater.apidrinkwater.usermanagement.model.HeightUnit;
import com.drinkwater.apidrinkwater.usermanagement.model.WeightUnit;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private BiologicalSex biologicalSex;
    private double weight;
    private WeightUnit weightUnit;
    private double height;
    private HeightUnit heightUnit;
    private AlarmSettings alarmSettings;
}
