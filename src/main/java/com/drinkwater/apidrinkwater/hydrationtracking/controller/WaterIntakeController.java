package com.drinkwater.apidrinkwater.hydrationtracking.controller;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeCreateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import com.drinkwater.apidrinkwater.hydrationtracking.service.WaterIntakeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/water-intake")
public class WaterIntakeController {

    private final WaterIntakeService waterIntakeService;

    public WaterIntakeController(WaterIntakeService waterIntakeService) {
        this.waterIntakeService = waterIntakeService;
    }

    @PostMapping
    public ResponseEntity<WaterIntake> createByUser(WaterIntakeCreateDTO dto) {
        WaterIntake waterIntake = this.waterIntakeService.createByUser(dto);

        return ResponseEntity.ok(waterIntake);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterIntake> findById(@PathVariable Long id) {
        WaterIntake waterIntake = this.waterIntakeService.findById(id);

        return ResponseEntity.ok(waterIntake);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<WaterIntake>> findAllByUserId(@PathVariable Long id) {
        List<WaterIntake> waterIntakes = this.waterIntakeService.findAllByUserId(id);

        return ResponseEntity.ok(waterIntakes);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WaterIntake> update(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        WaterIntake waterIntake = this.waterIntakeService.update(id, fields);

        return ResponseEntity.ok(waterIntake);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = this.waterIntakeService.delete(id);

        return ResponseEntity.ok(message);
    }
}
