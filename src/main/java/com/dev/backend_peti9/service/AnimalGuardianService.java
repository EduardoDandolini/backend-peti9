package com.dev.backend_peti9.service;

import com.dev.backend_peti9.dto.AnimalGuardianRequest;
import com.dev.backend_peti9.dto.AnimalGuardianResponse;
import com.dev.backend_peti9.exception.NotFoundException;
import com.dev.backend_peti9.exception.ValidationException;
import com.dev.backend_peti9.model.AnimalGuardian;
import com.dev.backend_peti9.repository.AnimalGuardianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalGuardianService {

    private final AnimalGuardianRepository animalGuardianRepository;

    public void save(AnimalGuardianRequest request) {
        if (animalGuardianRepository.existsByName(request.getName())) {
            throw new ValidationException("AnimalGuardian with name " + request.getName() + " already exists");
        }

        AnimalGuardian animalGuardian = AnimalGuardian.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .build();
        animalGuardianRepository.save(animalGuardian);
    }

    public AnimalGuardianResponse getAnimalGuardianById(Long id) {
        AnimalGuardian animalGuardian = animalGuardianRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("AnimalGuardian with id " + id + " not found"));
        return mapToAnimalGuardianResponse(animalGuardian);
    }

    public AnimalGuardianResponse getAnimalGuardianByLikedName(String name) {
        AnimalGuardian animalGuardian = animalGuardianRepository.findByLikedName(name)
                .orElseThrow(() -> new NotFoundException("AnimalGuardian with name " + name + " not found"));
        return mapToAnimalGuardianResponse(animalGuardian);
    }

    private AnimalGuardianResponse mapToAnimalGuardianResponse(AnimalGuardian animalGuardian) {
        return new AnimalGuardianResponse(animalGuardian.getId(),
                animalGuardian.getName(),
                animalGuardian.getSurname(),
                animalGuardian.getBirthDate(),
                animalGuardian.getAnimals());
    }

}
