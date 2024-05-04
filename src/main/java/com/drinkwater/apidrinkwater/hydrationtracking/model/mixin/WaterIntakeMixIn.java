package com.drinkwater.apidrinkwater.hydrationtracking.model.mixin;

import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class WaterIntakeMixIn {

    @JsonBackReference
    private User user;
}
