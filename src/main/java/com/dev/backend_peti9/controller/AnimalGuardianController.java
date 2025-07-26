package com.dev.backend_peti9.controller;

import com.dev.backend_peti9.dto.AnimalGuardianRequest;
import com.dev.backend_peti9.dto.AnimalGuardianResponse;
import com.dev.backend_peti9.service.AnimalGuardianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/animal-guardian")
@RequiredArgsConstructor
public class AnimalGuardianController {

    private final AnimalGuardianService animalGuardianService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody AnimalGuardianRequest request) {
        try {
            animalGuardianService.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{/id}")
    public ResponseEntity<AnimalGuardianResponse> getAnimalGuardianById(@PathVariable Long id) {
        return ResponseEntity.ok(animalGuardianService.getAnimalGuardianById(id));
    }

    @GetMapping("{/name}")
    public ResponseEntity<AnimalGuardianResponse> getAnimalGuardianByLikedName(@PathVariable String name) {
        return ResponseEntity.ok(animalGuardianService.getAnimalGuardianByLikedName(name));
    }

}
