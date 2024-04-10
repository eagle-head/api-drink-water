package com.drinkwater.apidrinkwater.hydrationtracking.service;

import com.drinkwater.apidrinkwater.hydrationtracking.repository.WaterIntakeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WaterIntakeService {

    private final WaterIntakeRepository waterIntakeRepository;

    public WaterIntakeService(WaterIntakeRepository waterIntakeRepository) {
        this.waterIntakeRepository = waterIntakeRepository;
    }

    @Transactional
    public void deleteAllWaterIntakesByUserId(Long userId) {
        this.waterIntakeRepository.deleteAllByUserId(userId);
    }
}
