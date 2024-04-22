package com.drinkwater.apidrinkwater.usermanagement.service;

import com.drinkwater.apidrinkwater.usermanagement.dto.UserCreateDTO;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserResponseDTO;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserUpdateDTO;
import com.drinkwater.apidrinkwater.usermanagement.exception.EmailAlreadyUsedException;
import com.drinkwater.apidrinkwater.usermanagement.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.drinkwater.apidrinkwater.usermanagement.repository.UserRepository;

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
            .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id + "."));
    }

    // Update method
    @Transactional
    public UserResponseDTO update(Long id, UserUpdateDTO updateDTO) {
        User existingUser = this.userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Unable to update user: No user found with ID " + id + "."));

        this.mapper.toEntity(updateDTO, existingUser);

        User savedUser = this.userRepository.save(existingUser);

        return this.mapper.toDto(savedUser);
    }

    // Delete method
    @Transactional
    public void delete(Long id) {
        this.userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Deletion failed: No user found with ID " + id + "."));

        this.userRepository.deleteById(id);
    }
}
