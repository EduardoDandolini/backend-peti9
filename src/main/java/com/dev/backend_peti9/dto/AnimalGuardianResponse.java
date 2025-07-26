package com.dev.backend_peti9.dto;

import com.dev.backend_peti9.model.Animal;

import java.time.LocalDate;
import java.util.List;

public record AnimalGuardianResponse(Long id, String name, String surname, LocalDate birthDate, List<Animal> animals) {

}
