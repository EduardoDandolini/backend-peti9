package com.dev.backend_peti9.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record AnimalGuardianRequest(String name, String surname, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate) {

}
