package com.dev.backend_peti9.controller;

import com.dev.backend_peti9.dto.AnimalGuardianRequest;
import com.dev.backend_peti9.dto.AnimalGuardianResponse;
import com.dev.backend_peti9.service.AnimalGuardianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/animal-guardian")
@RequiredArgsConstructor
@Tag(name = "AnimalGuardianController", description = "Controller for AnimalGuardian")
public class AnimalGuardianController {

    private final AnimalGuardianService animalGuardianService;

    @PostMapping
    @Operation(summary = "Save a new AnimalGuardian", description = "Save a new AnimalGuardian")
    public ResponseEntity<Void> save(@RequestBody AnimalGuardianRequest request) {
        animalGuardianService.save(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an AnimalGuardian by id", description = "Get an AnimalGuardian by id")
    public ResponseEntity<AnimalGuardianResponse> getAnimalGuardianById(@PathVariable Long id) {
        AnimalGuardianResponse response = animalGuardianService.getAnimalGuardianById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get an AnimalGuardian by name", description = "Get an AnimalGuardian by name")
    public ResponseEntity<List<AnimalGuardianResponse>> getAnimalGuardianByLikedName(@PathVariable String name) {
        List<AnimalGuardianResponse> response = animalGuardianService.getAnimalGuardianByLikedName(name);
        return ResponseEntity.ok(response);
    }
}
