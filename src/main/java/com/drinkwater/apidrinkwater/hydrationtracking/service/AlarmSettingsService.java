package com.drinkwater.apidrinkwater.hydrationtracking.service;

import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import com.drinkwater.apidrinkwater.hydrationtracking.repository.AlarmSettingsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class AlarmSettingsService {

    private final AlarmSettingsRepository alarmSettingsRepository;

    public AlarmSettingsService(AlarmSettingsRepository alarmSettingsRepository) {
        this.alarmSettingsRepository = alarmSettingsRepository;
    }

    @Transactional(readOnly = true)
    public AlarmSettings findById(Long id) {
        return this.alarmSettingsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("AlarmSettings not found."));
    }

    @Transactional
    public AlarmSettings update(Long id, Map<String, Object> fields) {
        AlarmSettings existingAlarmSettings = this.findById(id);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(AlarmSettings.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingAlarmSettings, value);
            }
        });

        return this.alarmSettingsRepository.save(existingAlarmSettings);
    }
}
