package com.drinkwater.apidrinkwater.hydrationtracking.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class AlarmSettings {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interval_minutes", nullable = false)
    private int intervalMinutes;

    @Column(name = "start_time", nullable = false)
    private OffsetDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private OffsetDateTime endTime;

    @Override
    public String toString() {
        return "AlarmSettings{" +
            "id=" + id +
            ", intervalMinutes=" + intervalMinutes +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            '}';
    }
}
