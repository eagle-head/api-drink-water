package com.drinkwater.apidrinkwater.hydrationtracking.controller;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsUpdateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.service.AlarmSettingsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/alarm-settings")
public class AlarmSettingsController {

    private final AlarmSettingsService alarmSettingsService;

    public AlarmSettingsController(AlarmSettingsService alarmSettingsService) {
        this.alarmSettingsService = alarmSettingsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlarmSettingsResponseDTO> findById(@PathVariable Long id) {
        AlarmSettingsResponseDTO alarmSettings = this.alarmSettingsService.findById(id);

        return ResponseEntity.ok(alarmSettings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlarmSettingsResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AlarmSettingsUpdateDTO dto) {
        AlarmSettingsResponseDTO alarmSettings = this.alarmSettingsService.update(id, dto);

        return ResponseEntity.ok(alarmSettings);
    }
}
