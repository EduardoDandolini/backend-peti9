package com.dev.backend_peti9.dto;

import com.dev.backend_peti9.model.Vaccine;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponse {

    private Long id;

    private String name;

    private String race;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String color;

    private Double weight;

    private List<Vaccine> vaccines;
}
