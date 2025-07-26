package com.dev.backend_peti9.dto;

import java.time.LocalDateTime;

public record AnimalGuardianRequest(String name, String surname, LocalDateTime birthDate) {
}
