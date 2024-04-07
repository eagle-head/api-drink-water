package com.drinkwater.apidrinkwater.usermanagement.service;

import com.drinkwater.apidrinkwater.usermanagement.exception.EmailAlreadyUsedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.drinkwater.apidrinkwater.usermanagement.repository.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user) {
        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyUsedException("The email provided is already in use.");
        }

        return this.userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found.")));
    }

    public String delete(Long id) {
        if (!this.userRepository.existsById(id)) {
            throw new EntityNotFoundException("Deletion is not necessary as the user does not exist.");
        }

        this.userRepository.deleteById(id);

        return "User successfully deleted.";
    }

    // TODO: Update user - PATCH '/update'


}
