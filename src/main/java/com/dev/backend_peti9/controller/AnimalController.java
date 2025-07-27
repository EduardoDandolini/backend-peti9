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
    public void save(@RequestBody AnimalRequest request) {
        try {
            animalService.save(request);
            ResponseEntity.ok().build();
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an Animal by id", description = "Get an Animal by id")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(animalService.getAnimalById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get an Animal by name", description = "Get an Animal by name")
    public ResponseEntity<List<AnimalResponse>> getAnimalByLikedName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(animalService.getAnimalByLikedName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an Animal", description = "Update an Animal")
    public void updateAnimal(@PathVariable Long id, @RequestBody AnimalRequest request) {
        try {
            animalService.updateAnimal(id, request);
            ResponseEntity.ok().build();
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an Animal by id", description = "Delete an Animal by id")
    public void deleteAnimalById(@PathVariable Long id) {
        animalService.deleteAnimalById(id);
    }

}
