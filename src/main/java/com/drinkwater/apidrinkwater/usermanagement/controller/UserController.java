package com.drinkwater.apidrinkwater.usermanagement.controller;

import com.drinkwater.apidrinkwater.usermanagement.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.drinkwater.apidrinkwater.usermanagement.service.UserService;

@RestController
@RequestMapping("/me")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User newUser = this.userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return this.userService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // TODO: Update user - PATCH '/update'

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = this.userService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
