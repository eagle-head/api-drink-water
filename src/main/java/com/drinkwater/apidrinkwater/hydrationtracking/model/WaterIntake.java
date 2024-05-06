package com.drinkwater.apidrinkwater.hydrationtracking.model;

import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "water_intakes")
public class WaterIntake {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "date_time_utc", unique = true, nullable = false)
    private OffsetDateTime dateTimeUTC;

    @Column(nullable = false)
    private double volume;

    @Enumerated(EnumType.STRING)
    @Column(name = "volume_unit", nullable = false)
    private VolumeUnit volumeUnit;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "WaterIntake{" +
            "id=" + id +
            ", dateTimeUTC=" + dateTimeUTC +
            ", volume=" + volume +
            ", volumeUnit=" + volumeUnit +
            ", userId=" + (user != null ? user.getId() : "null") +
            '}';
    }
}
