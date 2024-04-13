package com.drinkwater.apidrinkwater.usermanagement.service;

import com.drinkwater.apidrinkwater.hydrationtracking.service.WaterIntakeService;
import com.drinkwater.apidrinkwater.usermanagement.exception.EmailAlreadyUsedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
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
    private final WaterIntakeService waterIntakeService;

    public UserService(UserRepository userRepository, @Lazy WaterIntakeService waterIntakeService) {
        this.userRepository = userRepository;
        this.waterIntakeService = waterIntakeService;
    }

    @Transactional
    public User save(User user) {
        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyUsedException("The email provided is already in use.");
        }

        return this.userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    // tratar o erro de obejtos aninhandos, o update de AlarmSettings está
    // em seu próprio contexto
    @Transactional
    public User update(Long id, Map<String, Object> fields) {
        User existingUser = this.findById(id);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser, value);
            }
        });

        return this.userRepository.save(existingUser);
    }

    @Transactional
    public String delete(Long id) {
        this.findById(id);

        this.waterIntakeService.deleteAllWaterIntakesByUserId(id);

        this.userRepository.deleteById(id);

        return "User successfully deleted.";
    }
}
