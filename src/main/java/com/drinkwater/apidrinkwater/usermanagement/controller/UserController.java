package com.drinkwater.apidrinkwater.usermanagement.controller;

import com.drinkwater.apidrinkwater.usermanagement.dto.UserCreateDTO;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserResponseDTO;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.drinkwater.apidrinkwater.usermanagement.service.UserService;

@RestController
@RequestMapping("/me")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreateDTO dto) {
        UserResponseDTO newUser = this.userService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        UserResponseDTO user = this.userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
        UserResponseDTO user = this.userService.update(id, dto);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
