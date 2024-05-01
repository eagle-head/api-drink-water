package com.drinkwater.apidrinkwater.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Documented
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = TimeRangeValidator.class)
public @interface TimeRangeConstraint {

    String message() default "Validation error in time ranges";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
