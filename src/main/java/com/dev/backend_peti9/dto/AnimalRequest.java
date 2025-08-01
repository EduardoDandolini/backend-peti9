package com.dev.backend_peti9.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalRequest {

    private Long id;

    private String name;

    private String race;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String color;

    private Double weight;

    private Long animalGuardianId;
}
