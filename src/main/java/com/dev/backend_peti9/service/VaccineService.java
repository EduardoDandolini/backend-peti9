package com.dev.backend_peti9.service;

import com.dev.backend_peti9.dto.VaccineRequest;
import com.dev.backend_peti9.exception.NotFoundException;
import com.dev.backend_peti9.model.Animal;
import com.dev.backend_peti9.model.Vaccine;
import com.dev.backend_peti9.repository.AnimalRepository;
import com.dev.backend_peti9.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;

    public void save(VaccineRequest request) {
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new NotFoundException("Animal with id " + request.getAnimalId() + " not found"));
        vaccineRepository.save(Vaccine.builder()
                .applicationDate(request.getApplicationDate())
                .type(request.getType())
                .animal(animal)
                .build());
    }
}
