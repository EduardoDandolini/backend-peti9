package com.dev.backend_peti9.service;

import com.dev.backend_peti9.dto.AnimalRequest;
import com.dev.backend_peti9.dto.AnimalResponse;
import com.dev.backend_peti9.exception.NotFoundException;
import com.dev.backend_peti9.exception.ValidationException;
import com.dev.backend_peti9.model.Animal;
import com.dev.backend_peti9.model.AnimalGuardian;
import com.dev.backend_peti9.repository.AnimalGuardianRepository;
import com.dev.backend_peti9.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalGuardianRepository animalGuardianRepository;

    public void save(AnimalRequest request) {
        AnimalGuardian animalGuardian = animalGuardianRepository.findById(request.getAnimalGuardianId())
                .orElseThrow(() -> new NotFoundException("AnimalGuardian with id " + request.getAnimalGuardianId() + " not found"));

        if (animalRepository.existsByName(request.getName()) && animalGuardian.getId().equals(request.getAnimalGuardianId())) {
            throw new ValidationException("Animal with name " + request.getName() + " already exists");
        }

        animalRepository.save(Animal.builder()
                .name(request.getName())
                .race(request.getRace())
                .birthDate(request.getBirthDate())
                .color(request.getColor())
                .weight(request.getWeight())
                .animalGuardian(animalGuardian)
                .build());
    }

    public AnimalResponse getAnimalById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Animal with id " + id + " not found"));
        return mapToAnimalResponse(animal);
    }

    public List<AnimalResponse> getAnimalByLikedName(String name) {
        List<Animal> animalList = animalRepository.findByLikedName(name);
        if (animalList.isEmpty()) {
            throw new NotFoundException("No Animal found with name containing: " + name);
        }
        return animalList.stream()
                .map(this::mapToAnimalResponse)
                .toList();
    }

    //TODO Verificar por que não está atualizando o registro
    public void updateAnimal(Long id, AnimalRequest request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Animal with id " + request.getId() + " not found"));
        animalRepository.save(Animal.builder()
                .name(request.getName())
                .race(request.getRace())
                .birthDate(request.getBirthDate())
                .color(request.getColor())
                .weight(request.getWeight())
                .animalGuardian(animal.getAnimalGuardian())
                .build());
    }

    public void deleteAnimalById(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new NotFoundException("Animal with id " + id + " not found");
        }
        animalRepository.deleteById(id);
    }

    private AnimalResponse mapToAnimalResponse(Animal animal) {
        return new AnimalResponse(animal.getId(),
                animal.getName(),
                animal.getRace(),
                animal.getBirthDate(),
                animal.getColor(),
                animal.getWeight(),
                animal.getVaccines());
    }
}
