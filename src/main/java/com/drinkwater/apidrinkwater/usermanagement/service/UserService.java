package com.drinkwater.apidrinkwater.usermanagement.service;

import com.drinkwater.apidrinkwater.hydrationtracking.service.WaterIntakeService;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserCreateDTO;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserResponseDTO;
import com.drinkwater.apidrinkwater.usermanagement.exception.EmailAlreadyUsedException;
import com.drinkwater.apidrinkwater.usermanagement.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.drinkwater.apidrinkwater.usermanagement.repository.UserRepository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    // Create method
    @Transactional
    public UserResponseDTO save(UserCreateDTO dto) {
        if (this.userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyUsedException("The email provided is already in use.");
        }

        User newUser = this.mapper.toEntity(dto);
        User savedUser = this.userRepository.save(newUser);

        return this.mapper.toDto(savedUser);
    }

    // Read method
    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        return this.userRepository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    // tratar o erro de obejtos aninhandos, o update de AlarmSettings está
    // em seu próprio contexto
    // Update method
    @Transactional
    public UserResponseDTO update(Long id, Map<String, Object> fields) {
        User existingUser = this.userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser, value);
            }
        });

        User savedUser = this.userRepository.save(existingUser);

        return this.mapper.toDto(savedUser);
    }

    // Delete method
    @Transactional
    public void delete(Long id) {
        this.userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        this.userRepository.deleteById(id);
    }
}
