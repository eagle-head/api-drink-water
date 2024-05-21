package com.drinkwater.apidrinkwater.hydrationtracking.repository;

import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WaterIntakeRepository extends JpaRepository<WaterIntake, Long> {

    void deleteAllByUserId(Long id);

    List<WaterIntake> findAllByUserId(Long userId);

    boolean existsByDateTimeUTCAndUserId(OffsetDateTime dateTimeUTC, Long userId);

    Optional<WaterIntake> findByCode(byte[] code);
}
