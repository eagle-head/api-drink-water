package com.drinkwater.apidrinkwater.usermanagement.model;

import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "biological_sex", nullable = false)
    private BiologicalSex biologicalSex;

    @Column(nullable = false)
    private double weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "weight_unit", nullable = false)
    private WeightUnit weightUnit;

    @Column(nullable = false)
    private double height;

    @Enumerated(EnumType.STRING)
    @Column(name = "height_unit", nullable = false)
    private HeightUnit heightUnit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alarm_settings_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private AlarmSettings alarmSettings;
}
