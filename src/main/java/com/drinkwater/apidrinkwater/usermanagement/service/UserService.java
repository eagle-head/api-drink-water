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

    private final static String USER_NOT_FOUND_MESSAGE = "User not found with ID: ";
    private final static String EMAIL_ALREADY_USED_MESSAGE = "The email provided is already in use.";

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
            throw new EmailAlreadyUsedException(EMAIL_ALREADY_USED_MESSAGE);
        }

        User newUser = this.mapper.toEntity(dto);
        User savedUser = this.userRepository.save(newUser);

        return this.mapper.toDto(savedUser);
    }

    // Read method
    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        User user = this.findUserById(id);

        return this.mapper.toDto(user);
    }

    // Update method
    @Transactional
    public UserResponseDTO update(Long id, UserUpdateDTO updateDTO) {
        User existingUser = this.findUserById(id);

        if (updateDTO.getEmail() != null && this.userRepository.existsByEmailAndIdNot(updateDTO.getEmail(), id)) {
            throw new EmailAlreadyUsedException(EMAIL_ALREADY_USED_MESSAGE);
        }

        this.mapper.toEntity(updateDTO, existingUser);

        User savedUser = this.userRepository.save(existingUser);

        return this.mapper.toDto(savedUser);
    }

    // Delete method
    @Transactional
    public void delete(Long id) {
        this.existsById(id);

        this.userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return this.userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_MESSAGE + id + "."));
    }

    @Transactional(readOnly = true)
    public void existsById(Long id) {
        boolean userExists = this.userRepository.existsById(id);

        if (!userExists) {
            throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE + id + ".");
        }
    }
}
