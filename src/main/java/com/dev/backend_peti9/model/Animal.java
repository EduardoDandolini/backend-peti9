package com.dev.backend_peti9.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Animal extends BaseEntity {

    private String name;

    private String race;

    private LocalDate birthDate;

    private String color;

    private Double weight;

    private List<LocalDate> vaccinationDates;

    private String typeVaccine;

    @ManyToOne
    @JoinColumn(name = "animal_guardian_id")
    private AnimalGuardian animalGuardian;

}
