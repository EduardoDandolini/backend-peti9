package com.dev.backend_peti9.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Animal extends BaseEntity {

    private String name;

    private String race;

    private LocalDateTime birthDate;

    private String color;

    private Double weight;

    private List<LocalDateTime> vaccinationDates;

    private String typeVaccine;

    @ManyToOne
    @JoinColumn(name = "animal_guardian_id")
    private AnimalGuardian animalGuardian;

}
