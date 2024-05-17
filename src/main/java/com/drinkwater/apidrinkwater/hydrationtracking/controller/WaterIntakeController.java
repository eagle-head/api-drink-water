package com.drinkwater.apidrinkwater.hydrationtracking.controller;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeCreateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeUpdateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.service.WaterIntakeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/water-intakes")
public class WaterIntakeController {

    private final WaterIntakeService waterIntakeService;

    public WaterIntakeController(WaterIntakeService waterIntakeService) {
        this.waterIntakeService = waterIntakeService;
    }

    @PostMapping
    public ResponseEntity<WaterIntakeResponseDTO> create(@PathVariable Long userId,
                                                         @Valid @RequestBody WaterIntakeCreateDTO dto) {
        WaterIntakeResponseDTO newWaterIntake = this.waterIntakeService.create(userId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newWaterIntake);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterIntakeResponseDTO> findById(@PathVariable Long id, @PathVariable Long userId) {
        WaterIntakeResponseDTO waterIntake = this.waterIntakeService.findById(userId, id);

        return ResponseEntity.ok(waterIntake);
    }

    @GetMapping
    public ResponseEntity<List<WaterIntakeResponseDTO>> findAllByUserId(@PathVariable Long userId) {
        List<WaterIntakeResponseDTO> waterIntakes = this.waterIntakeService.findAllByUserId(userId);

        return ResponseEntity.ok(waterIntakes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterIntakeResponseDTO> update(@PathVariable Long id,
                                                         @Valid @RequestBody WaterIntakeUpdateDTO dto) {
        WaterIntakeResponseDTO waterIntake = this.waterIntakeService.update(id, dto);

        return ResponseEntity.ok(waterIntake);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long id) {
        this.waterIntakeService.delete(userId, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllWaterIntakesByUserId(@PathVariable Long userId) {
        this.waterIntakeService.deleteAllWaterIntakesByUserId(userId);

        return ResponseEntity.noContent().build();
    }
}
