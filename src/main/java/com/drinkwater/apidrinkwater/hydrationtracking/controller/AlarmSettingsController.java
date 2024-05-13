package com.drinkwater.apidrinkwater.hydrationtracking.controller;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsUpdateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.service.AlarmSettingsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users/{userId}/alarm-settings")
public class AlarmSettingsController {

    private final AlarmSettingsService alarmSettingsService;

    public AlarmSettingsController(AlarmSettingsService alarmSettingsService) {
        this.alarmSettingsService = alarmSettingsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlarmSettingsResponseDTO> findById(@PathVariable Long userId, @PathVariable Long id) {
        AlarmSettingsResponseDTO alarmSettings = this.alarmSettingsService.findById(userId, id);

        return ResponseEntity.ok(alarmSettings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlarmSettingsResponseDTO> update(@PathVariable Long userId, @PathVariable Long id,
                                                           @Valid @RequestBody AlarmSettingsUpdateDTO dto) {
        AlarmSettingsResponseDTO alarmSettings = this.alarmSettingsService.update(userId, id, dto);

        return ResponseEntity.ok(alarmSettings);
    }
}
