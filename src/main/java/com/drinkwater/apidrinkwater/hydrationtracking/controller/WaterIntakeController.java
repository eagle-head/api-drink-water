package com.drinkwater.apidrinkwater.hydrationtracking.controller;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeCreateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeFilterDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeUpdateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.service.WaterIntakeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{code}")
    public ResponseEntity<WaterIntakeResponseDTO> findByCode(@PathVariable UUID code, @PathVariable Long userId) {
        WaterIntakeResponseDTO waterIntake = this.waterIntakeService.findByCode(userId, code);

        return ResponseEntity.ok(waterIntake);
    }

    @PutMapping("/{code}")
    public ResponseEntity<WaterIntakeResponseDTO> update(@PathVariable UUID code,
                                                         @Valid @RequestBody WaterIntakeUpdateDTO dto) {
        WaterIntakeResponseDTO waterIntake = this.waterIntakeService.update(code, dto);

        return ResponseEntity.ok(waterIntake);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable UUID code) {
        this.waterIntakeService.delete(userId, code);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllWaterIntakesByUserId(@PathVariable Long userId) {
        this.waterIntakeService.deleteAllWaterIntakesByUserId(userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<WaterIntakeResponseDTO>> filterWaterIntakes(@PathVariable Long userId,
                                                                           WaterIntakeFilterDTO filterDTO,
                                                                           Pageable pageable) {

        Page<WaterIntakeResponseDTO> filteredWaterIntakes =
            this.waterIntakeService.findFilteredWaterIntakes(userId, filterDTO, pageable);

        return ResponseEntity.ok(filteredWaterIntakes);
    }
}
