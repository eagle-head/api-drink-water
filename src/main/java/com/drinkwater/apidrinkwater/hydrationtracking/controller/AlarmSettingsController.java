package com.drinkwater.apidrinkwater.hydrationtracking.controller;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
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
    public ResponseEntity<AlarmSettings> findById(@PathVariable Long id) {
        AlarmSettings alarmSettings = this.alarmSettingsService.findById(id);

        return ResponseEntity.ok(alarmSettings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlarmSettings> update(@PathVariable Long id, @Valid @RequestBody AlarmSettingsDTO dto) {
        AlarmSettings alarmSettings = this.alarmSettingsService.update(id, dto);

        return ResponseEntity.ok(alarmSettings);
    }
}
