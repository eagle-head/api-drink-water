package com.drinkwater.apidrinkwater.hydrationtracking.repository;

import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface WaterIntakeRepository extends JpaRepository<WaterIntake, Long>,
                                        JpaSpecificationExecutor<WaterIntake> {

    void deleteAllByUserId(Long id);

    boolean existsByDateTimeUTCAndUserId(OffsetDateTime dateTimeUTC, Long userId);

    Optional<WaterIntake> findByCode(byte[] code);
}
