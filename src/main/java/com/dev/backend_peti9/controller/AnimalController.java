package com.dev.backend_peti9.controller;

import com.dev.backend_peti9.dto.AnimalRequest;
import com.dev.backend_peti9.dto.AnimalResponse;
import com.dev.backend_peti9.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/animals")
@RequiredArgsConstructor
@Tag(name = "AnimalController", description = "Animal Controller")
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    @Operation(summary = "Save a new Animal", description = "Save a new Animal")
    public ResponseEntity<Void> save(@RequestBody AnimalRequest request) {
        animalService.save(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an Animal by id", description = "Get an Animal by id")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable Long id) {
        AnimalResponse animal = animalService.getAnimalById(id);
        return ResponseEntity.ok(animal);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get an Animal by name", description = "Get an Animal by name")
    public ResponseEntity<List<AnimalResponse>> getAnimalByLikedName(@PathVariable String name) {
        List<AnimalResponse> animals = animalService.getAnimalByLikedName(name);
        return ResponseEntity.ok(animals);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an Animal", description = "Update an Animal")
    public ResponseEntity<Void> updateAnimal(@PathVariable Long id, @RequestBody AnimalRequest request) {
        animalService.updateAnimal(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an Animal by id", description = "Delete an Animal by id")
    public ResponseEntity<Void> deleteAnimalById(@PathVariable Long id) {
        animalService.deleteAnimalById(id);
        return ResponseEntity.noContent().build();
    }
}
