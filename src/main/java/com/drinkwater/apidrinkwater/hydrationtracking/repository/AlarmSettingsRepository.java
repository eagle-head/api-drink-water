package com.drinkwater.apidrinkwater.hydrationtracking.repository;

import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmSettingsRepository extends JpaRepository<AlarmSettings, Long> {
}
