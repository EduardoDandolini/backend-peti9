package com.dev.backend_peti9.dto;

import com.dev.backend_peti9.model.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class AnimalResponse {

    private Long id;

    private String name;

    private String race;

    private LocalDate birthDate;

    private String color;

    private Double weight;

    private List<Vaccine> vaccines;
}
