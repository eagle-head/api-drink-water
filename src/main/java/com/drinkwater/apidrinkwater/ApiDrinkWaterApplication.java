package com.drinkwater.apidrinkwater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class ApiDrinkWaterApplication {

    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(ApiDrinkWaterApplication.class, args);
    }
}
