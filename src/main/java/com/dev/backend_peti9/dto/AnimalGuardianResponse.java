package com.dev.backend_peti9.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AnimalGuardianResponse(Long id, String name, String surname, LocalDateTime birthDate, List<?> animals) {

}
