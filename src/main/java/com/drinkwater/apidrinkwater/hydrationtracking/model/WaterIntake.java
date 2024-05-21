package com.drinkwater.apidrinkwater.hydrationtracking.model;

import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.drinkwater.apidrinkwater.util.UUIDConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.UUID;

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
    @Column(name = "code", nullable = false, updatable = false, unique = true, columnDefinition = "BINARY(16)")
    private byte[] code;

    @EqualsAndHashCode.Include
    @Column(name = "date_time_utc", unique = true, nullable = false)
    private OffsetDateTime dateTimeUTC;

    @Column(nullable = false)
    private int volume;

    @Enumerated(EnumType.STRING)
    @Column(name = "volume_unit", nullable = false)
    private VolumeUnit volumeUnit;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public WaterIntake() {
        this.code = UUIDConverter.toBytes(UUID.randomUUID());
    }

    public UUID getCode() {
        return UUIDConverter.toUUID(this.code);
    }

    public void setCode(UUID code) {
        this.code = UUIDConverter.toBytes(code);
    }

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
