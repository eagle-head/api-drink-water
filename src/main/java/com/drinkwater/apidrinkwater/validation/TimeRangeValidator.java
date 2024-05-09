package com.drinkwater.apidrinkwater.validation;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class TimeRangeValidator implements ConstraintValidator<TimeRangeConstraint, AlarmSettingsDTO> {

    private static final long FIFTEEN_MINUTES = 15;

    @Override
    public boolean isValid(AlarmSettingsDTO dto, ConstraintValidatorContext context) {
        if (dto.getStartTime() == null || dto.getEndTime() == null) {
            return true;
        }

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        if (dto.getStartTime().isAfter(now) || dto.getEndTime().isAfter(now)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{time.range.future}").addConstraintViolation();

            return false;
        }

        if (dto.getStartTime().isAfter(dto.getEndTime())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{time.range.order}").addConstraintViolation();

            return false;
        }

        long difference = ChronoUnit.MINUTES.between(dto.getStartTime(), dto.getEndTime());
        if (difference < FIFTEEN_MINUTES) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{time.range.min.interval}").addConstraintViolation();

            return false;
        }

        return true;
    }
}
