package com.drinkwater.apidrinkwater.hydrationtracking.service;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.mapper.AlarmSettingsMapper;
import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import com.drinkwater.apidrinkwater.hydrationtracking.repository.AlarmSettingsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AlarmSettingsService {

    private final AlarmSettingsRepository alarmSettingsRepository;
    private final AlarmSettingsMapper mapper;

    public AlarmSettingsService(AlarmSettingsRepository alarmSettingsRepository, AlarmSettingsMapper mapper) {
        this.alarmSettingsRepository = alarmSettingsRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public AlarmSettings findById(Long id) {
        return this.alarmSettingsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("AlarmSettings not found."));
    }

    @Transactional
    public AlarmSettings update(Long id, AlarmSettingsDTO dto) {
        AlarmSettings existingAlarmSettings = this.findById(id);
        AlarmSettings updatedAlarmSettings = this.mapper.toEntity(dto);
        updatedAlarmSettings.setId(existingAlarmSettings.getId());

        return this.alarmSettingsRepository.save(updatedAlarmSettings);
    }
}
