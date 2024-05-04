package com.drinkwater.apidrinkwater.jackson;

import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import com.drinkwater.apidrinkwater.hydrationtracking.model.mixin.WaterIntakeMixIn;
import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.drinkwater.apidrinkwater.usermanagement.model.mixin.UserMixIn;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixInModule extends SimpleModule {

    public JacksonMixInModule() {
        setMixInAnnotation(User.class, UserMixIn.class);
        setMixInAnnotation(WaterIntake.class, WaterIntakeMixIn.class);
    }
}
