package com.dev.backend_peti9.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vaccine> vaccines = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "animal_guardian_id")
    @JsonBackReference
    private AnimalGuardian animalGuardian;

}
