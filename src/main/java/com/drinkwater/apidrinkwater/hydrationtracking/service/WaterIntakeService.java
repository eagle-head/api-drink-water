package com.drinkwater.apidrinkwater.hydrationtracking.service;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeCreateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeFilterDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeUpdateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.exception.DuplicateDateTimeException;
import com.drinkwater.apidrinkwater.hydrationtracking.mapper.WaterIntakeMapper;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import com.drinkwater.apidrinkwater.hydrationtracking.repository.WaterIntakeRepository;
import com.drinkwater.apidrinkwater.hydrationtracking.specification.WaterIntakeSpecification;
import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.drinkwater.apidrinkwater.usermanagement.service.UserService;
import com.drinkwater.apidrinkwater.util.UUIDConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WaterIntakeService {

    private final WaterIntakeRepository waterIntakeRepository;
    private final UserService userService;
    private final WaterIntakeMapper mapper;

    public WaterIntakeService(WaterIntakeRepository waterIntakeRepository, UserService userService,
                              WaterIntakeMapper mapper) {
        this.waterIntakeRepository = waterIntakeRepository;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Transactional
    public WaterIntakeResponseDTO create(Long userId, WaterIntakeCreateDTO dto) {
        User user = this.userService.findUserById(userId);

        if (this.waterIntakeRepository.existsByDateTimeUTCAndUserId(dto.getDateTimeUTC(), userId)) {
            throw new DuplicateDateTimeException("A water intake record already exists for the specified date"
                + " and time for this user.");
        }

        WaterIntake newWaterIntake = new WaterIntake();
        newWaterIntake.setUser(user);
        this.mapper.toEntity(dto, newWaterIntake);
        WaterIntake savedWaterIntake = this.waterIntakeRepository.save(newWaterIntake);

        return this.mapper.toDto(savedWaterIntake);
    }

    @Transactional(readOnly = true)
    public WaterIntakeResponseDTO findByCode(Long userId, UUID code) {
        this.userService.existsById(userId);
        WaterIntake waterIntake = this.findWaterIntakeByCode(UUIDConverter.toBytes(code));

        return this.mapper.toDto(waterIntake);
    }

    @Transactional
    public WaterIntakeResponseDTO update(UUID code, WaterIntakeUpdateDTO dto) {
        WaterIntake existingWaterIntake = this.findWaterIntakeByCode(UUIDConverter.toBytes(code));
        WaterIntake updatedWaterIntake = this.mapper.toEntity(dto, existingWaterIntake);
        WaterIntake saved = this.waterIntakeRepository.save(updatedWaterIntake);

        return this.mapper.toDto(saved);
    }

    @Transactional
    public void delete(Long userId, UUID code) {
        this.userService.existsById(userId);
        WaterIntake waterIntake = this.findWaterIntakeByCode(UUIDConverter.toBytes(code));

        this.waterIntakeRepository.delete(waterIntake);
    }

    @Transactional
    public void deleteAllWaterIntakesByUserId(Long userId) {
        this.userService.existsById(userId);

        this.waterIntakeRepository.deleteAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public WaterIntake findWaterIntakeByCode(byte[] code) {
        return this.waterIntakeRepository.findByCode(code)
            .orElseThrow(() -> new EntityNotFoundException("Water Intake record not found."));
    }

    @Transactional(readOnly = true)
    public Page<WaterIntakeResponseDTO> findFilteredWaterIntakes(Long userId, WaterIntakeFilterDTO filterDTO,
                                                                                        Pageable pageable) {

        User user = this.userService.findUserById(userId);
        Specification<WaterIntake> spec = WaterIntakeSpecification.getSpecifications(user, filterDTO);
        Page<WaterIntake> page = this.waterIntakeRepository.findAll(spec, pageable);

        return page.map(this.mapper::toDto);
    }
}
