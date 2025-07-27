package com.dev.backend_peti9.dto;

import com.dev.backend_peti9.model.Animal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalGuardianResponse {

    private Long id;

    private String name;

    private String surname;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private List<Animal> animals;

}
