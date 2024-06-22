package com.drinkwater.apidrinkwater.email.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
@Component
@ConfigurationProperties("drink-water.email")
public class EmailProperties {

    @NotNull
    private String sender;
}
