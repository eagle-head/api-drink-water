package com.drinkwater.apidrinkwater.usermanagement.model.mixin;

import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserMixIn {

    @JsonIgnore
    private OffsetDateTime createdAt;

    @JsonIgnore
    private OffsetDateTime updatedAt;

    @JsonIgnore
    private AlarmSettings alarmSettings;

    @JsonIgnore
    private List<WaterIntake> waterIntakes = new ArrayList<>();
}
