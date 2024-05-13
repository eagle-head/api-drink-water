package com.drinkwater.apidrinkwater.hydrationtracking.service;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeCreateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeUpdateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.exception.DuplicateDateTimeException;
import com.drinkwater.apidrinkwater.hydrationtracking.mapper.WaterIntakeMapper;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import com.drinkwater.apidrinkwater.hydrationtracking.repository.WaterIntakeRepository;
import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.drinkwater.apidrinkwater.usermanagement.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public WaterIntakeResponseDTO create(Long userId, WaterIntakeCreateDTO dto) throws DuplicateDateTimeException {
        User user = this.userService.findUserById(userId);

        if (this.waterIntakeRepository.existsByDateTimeUTCAndUserId(dto.getDateTimeUTC(), userId)) {
            throw new DuplicateDateTimeException("A water intake record already exists for the specified date and time for this user.");
        }

        WaterIntake newWaterIntake = new WaterIntake();
        newWaterIntake.setUser(user);
        this.mapper.toEntity(dto, newWaterIntake);
        WaterIntake savedWaterIntake = this.waterIntakeRepository.save(newWaterIntake);

        return this.mapper.toDto(savedWaterIntake);
    }

    @Transactional(readOnly = true)
    public WaterIntakeResponseDTO findById(Long userId, Long id) {
        this.userService.findUserById(userId);
        WaterIntake waterIntake = this.findWaterIntakeById(id);

        return this.mapper.toDto(waterIntake);
    }

    @Transactional(readOnly = true)
    public List<WaterIntakeResponseDTO> findAllByUserId(Long userId) {
        return this.waterIntakeRepository.findAllByUserId(userId)
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public WaterIntakeResponseDTO update(Long id, WaterIntakeUpdateDTO dto) {
        WaterIntake existingWaterIntake = this.findWaterIntakeById(id);
        WaterIntake updatedWaterIntake = this.mapper.toEntity(dto, existingWaterIntake);
        WaterIntake saved = this.waterIntakeRepository.save(updatedWaterIntake);

        return this.mapper.toDto(saved);
    }

    @Transactional
    public void delete(Long userId, Long id) {
        this.userService.findUserById(userId);
        this.findWaterIntakeById(id);

        this.waterIntakeRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllWaterIntakesByUserId(Long userId) {
        this.userService.findUserById(userId);

        this.waterIntakeRepository.deleteAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public WaterIntake findWaterIntakeById(Long id) {
        return this.waterIntakeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Water Intake record not found."));
    }
}
