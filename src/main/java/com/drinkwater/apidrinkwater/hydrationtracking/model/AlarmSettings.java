package com.drinkwater.apidrinkwater.hydrationtracking.model;

import com.drinkwater.apidrinkwater.usermanagement.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.util.Date;

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
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @OneToOne(mappedBy = "alarmSettings")
    @JsonBackReference
    private User user;
}
