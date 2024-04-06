package com.drinkwater.apidrinkwater.usermanagement.service;

import com.drinkwater.apidrinkwater.usermanagement.exception.EmailAlreadyUsedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.drinkwater.apidrinkwater.usermanagement.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user) {
        if (emailExists(user.getEmail())) {
            throw new EmailAlreadyUsedException("The email " + user.getEmail() + " is already in use.");
        }

        return this.userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id)));
    }

    private boolean emailExists(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    // TODO: Update user - PATCH '/update'

    // TODO: Delete user - DELETE '/delete'
}
