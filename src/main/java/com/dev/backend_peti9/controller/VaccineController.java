package com.dev.backend_peti9.controller;

import com.dev.backend_peti9.dto.VaccineRequest;
import com.dev.backend_peti9.service.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vaccine")
@RequiredArgsConstructor
@Tag(name = "VaccineController", description = "Vaccine Controller")
public class VaccineController {

    private final VaccineService vaccineService;

    @PostMapping
    @Operation(summary = "Save a new Vaccine", description = "Save a new Vaccine")
    public ResponseEntity<Void> save(@RequestBody VaccineRequest request) {
        vaccineService.save(request);
        return ResponseEntity.status(201).build();
    }
}
