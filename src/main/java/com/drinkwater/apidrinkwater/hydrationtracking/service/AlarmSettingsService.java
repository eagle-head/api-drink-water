package com.drinkwater.apidrinkwater.hydrationtracking.service;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsUpdateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.mapper.AlarmSettingsMapper;
import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import com.drinkwater.apidrinkwater.hydrationtracking.repository.AlarmSettingsRepository;
import com.drinkwater.apidrinkwater.usermanagement.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AlarmSettingsService {

    private final AlarmSettingsRepository alarmSettingsRepository;
    private final AlarmSettingsMapper mapper;
    private final UserService userService;

    public AlarmSettingsService(AlarmSettingsRepository alarmSettingsRepository, AlarmSettingsMapper mapper,
                                UserService userService) {
        this.alarmSettingsRepository = alarmSettingsRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public AlarmSettingsResponseDTO findById(Long userId, Long id) {
        this.userService.existsById(userId);
        AlarmSettings alarmSettings = this.findAlarmSettingsById(id);

        return this.mapper.toDto(alarmSettings);
    }

    @Transactional
    public AlarmSettingsResponseDTO update(Long userId, Long id, AlarmSettingsUpdateDTO dto) {
        this.userService.existsById(userId);
        AlarmSettings existingAlarmSettings = this.findAlarmSettingsById(id);
        AlarmSettings updatedAlarmSettings = this.mapper.toEntity(dto);
        updatedAlarmSettings.setId(existingAlarmSettings.getId());
        AlarmSettings savedAlarmSettings = this.alarmSettingsRepository.save(updatedAlarmSettings);

        return this.mapper.toDto(savedAlarmSettings);
    }

    private AlarmSettings findAlarmSettingsById(Long id) {
        return this.alarmSettingsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("AlarmSettings not found."));
    }
}
