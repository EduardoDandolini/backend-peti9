package com.dev.backend_peti9.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineRequest {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate applicationDate;

    private String type;

    private Long animalId;

}
