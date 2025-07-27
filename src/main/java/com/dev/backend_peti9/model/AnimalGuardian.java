package com.dev.backend_peti9.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnimalGuardian extends BaseEntity{

    private String name;

    private String surname;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "animalGuardian")
    @JsonManagedReference
    private List<Animal> animals;

}
