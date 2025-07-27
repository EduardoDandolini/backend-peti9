package com.dev.backend_peti9.service;

import com.dev.backend_peti9.dto.AnimalGuardianRequest;
import com.dev.backend_peti9.dto.AnimalGuardianResponse;
import com.dev.backend_peti9.exception.NotFoundException;
import com.dev.backend_peti9.exception.ValidationException;
import com.dev.backend_peti9.model.AnimalGuardian;
import com.dev.backend_peti9.repository.AnimalGuardianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<AnimalGuardianResponse> getAnimalGuardianByLikedName(String name) {
        List<AnimalGuardian> animalGuardianList = animalGuardianRepository.findByLikedName(name.toLowerCase());
        if (animalGuardianList.isEmpty()) {
            throw new NotFoundException("No AnimalGuardian found with name containing: " + name);
        }
        return animalGuardianList.stream()
                .map(this::mapToAnimalGuardianResponse)
                .toList();
    }

    private AnimalGuardianResponse mapToAnimalGuardianResponse(AnimalGuardian animalGuardian) {
        return new AnimalGuardianResponse(animalGuardian.getId(),
                animalGuardian.getName(),
                animalGuardian.getSurname(),
                animalGuardian.getBirthDate(),
                animalGuardian.getAnimals());
    }

}
