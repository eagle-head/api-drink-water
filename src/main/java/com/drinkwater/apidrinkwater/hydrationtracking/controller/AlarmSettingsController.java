package com.drinkwater.apidrinkwater.hydrationtracking.controller;

import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import com.drinkwater.apidrinkwater.hydrationtracking.service.AlarmSettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PatchMapping("/{id}")
    public ResponseEntity<AlarmSettings> update(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        AlarmSettings alarmSettings = this.alarmSettingsService.update(id, fields);

        return ResponseEntity.ok(alarmSettings);
    }
}
