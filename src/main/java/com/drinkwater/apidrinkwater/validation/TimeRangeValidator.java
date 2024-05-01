package com.drinkwater.apidrinkwater.validation;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsCreateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

public class TimeRangeValidator implements ConstraintValidator<TimeRangeConstraint, AlarmSettingsCreateDTO> {

    private static final long FIFTEEN_MINUTES_IN_MILLIS = 15 * 60 * 1000;

    @Override
    public boolean isValid(AlarmSettingsCreateDTO dto, ConstraintValidatorContext context) {
        if (dto.getStartTime() == null || dto.getEndTime() == null) {
            return true;
        }

        Date now = new Date();
        if (dto.getStartTime().after(now) || dto.getEndTime().after(now)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{time.range.future}").addConstraintViolation();

            return false;
        }

        if (dto.getStartTime().after(dto.getEndTime())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{time.range.order}").addConstraintViolation();

            return false;
        }

        long difference = dto.getEndTime().getTime() - dto.getStartTime().getTime();
        if (difference < FIFTEEN_MINUTES_IN_MILLIS) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{time.range.min.interval}").addConstraintViolation();

            return false;
        }

        return true;
    }
}
