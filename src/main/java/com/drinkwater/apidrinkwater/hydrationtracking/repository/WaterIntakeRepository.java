package com.drinkwater.apidrinkwater.hydrationtracking.repository;

import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterIntakeRepository extends JpaRepository<WaterIntake, Long> {

    void deleteAllByUserId(Long id);

    List<WaterIntake> findAllByUserId(Long userId);
}