package com.drinkwater.apidrinkwater.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drinkwater.apidrinkwater.usermanagement.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    Optional<User> findById(Long id);
    boolean existsByEmail(String email);
}
