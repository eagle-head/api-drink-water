package com.drinkwater.apidrinkwater.hydrationtracking.service;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsUpdateDTO;
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
    public AlarmSettingsResponseDTO findById(Long id) {
        AlarmSettings alarmSettings = this.findAlarmSettingsById(id);

        return mapper.toDto(alarmSettings);
    }

    @Transactional
    public AlarmSettingsResponseDTO update(Long id, AlarmSettingsUpdateDTO dto) {
        AlarmSettings existingAlarmSettings = this.findAlarmSettingsById(id);
        AlarmSettings updatedAlarmSettings = this.mapper.toEntity(dto);
        updatedAlarmSettings.setId(existingAlarmSettings.getId());
        AlarmSettings savedAlarmSettings = this.alarmSettingsRepository.save(updatedAlarmSettings);

        return this.mapper.toDto(savedAlarmSettings);
    }

    private AlarmSettings findAlarmSettingsById(Long id) {
        return alarmSettingsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("AlarmSettings not found."));
    }
}
