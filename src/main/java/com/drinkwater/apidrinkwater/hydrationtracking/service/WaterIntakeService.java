package com.drinkwater.apidrinkwater.hydrationtracking.service;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeCreateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.mapper.WaterIntakeMapper;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import com.drinkwater.apidrinkwater.hydrationtracking.repository.WaterIntakeRepository;
import com.drinkwater.apidrinkwater.usermanagement.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class WaterIntakeService {

    private final WaterIntakeRepository waterIntakeRepository;
    private final UserService userService;
    private final WaterIntakeMapper mapper;

    public WaterIntakeService(WaterIntakeRepository waterIntakeRepository, UserService userService, WaterIntakeMapper mapper) {
        this.waterIntakeRepository = waterIntakeRepository;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Transactional
    public WaterIntake createByUser(WaterIntakeCreateDTO dto) {
        this.userService.findById(dto.getUserId());

        WaterIntake waterIntake = this.mapper.toCreateDto(dto);

        return this.waterIntakeRepository.save(waterIntake);
    }

    @Transactional(readOnly = true)
    public WaterIntake findById(Long id) {
        return this.waterIntakeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    @Transactional(readOnly = true)
    public List<WaterIntake> findAllByUserId(Long userId) {
        return this.waterIntakeRepository.findAllByUserId(userId);
    }

    @Transactional
    public WaterIntake update(Long id, Map<String, Object> fields) {
        WaterIntake existingWaterIntake = this.findById(id);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(WaterIntake.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingWaterIntake, value);
            }
        });

        return this.waterIntakeRepository.save(existingWaterIntake);
    }

    @Transactional
    public String delete(Long id) {
        this.findById(id);

        this.waterIntakeRepository.deleteById(id);

        return "Water intake successfully deleted.";
    }

    @Transactional
    public void deleteAllWaterIntakesByUserId(Long userId) {
        this.waterIntakeRepository.deleteAllByUserId(userId);
    }
}
