package com.dev.backend_peti9.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalGuardianRequest {

    private String name;

    private String surname;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

}
