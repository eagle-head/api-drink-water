package com.drinkwater.apidrinkwater.hydrationtracking.repository;

import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterIntakeRepository extends JpaRepository<WaterIntake, Long> {

    void deleteByUserId(Long id);
}
