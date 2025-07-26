package com.dev.backend_peti9.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class AnimalGuardian extends BaseEntity{

    private String name;

    private String surname;

    private LocalDateTime birthDate;

    @OneToMany(mappedBy = "animalGuardian")
    private List<Animal> animals;

}
