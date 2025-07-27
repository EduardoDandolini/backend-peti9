package com.dev.backend_peti9.service;

import com.dev.backend_peti9.dto.AnimalRequest;
import com.dev.backend_peti9.dto.AnimalResponse;
import com.dev.backend_peti9.exception.NotFoundException;
import com.dev.backend_peti9.exception.ValidationException;
import com.dev.backend_peti9.model.Animal;
import com.dev.backend_peti9.model.AnimalGuardian;
import com.dev.backend_peti9.repository.AnimalGuardianRepository;
import com.dev.backend_peti9.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private AnimalGuardianRepository animalGuardianRepository;

    @InjectMocks
    private AnimalService animalService;

    private Animal animal;
    private AnimalRequest request;
    private AnimalGuardian animalGuardian;

    @BeforeEach
    void setUp() {
        animalGuardian = AnimalGuardian.builder()
                .name("John Doe")
                .surname("Doe")
                .birthDate(LocalDate.of(1990, 1, 1))
                .animals(Collections.emptyList())
                .build();

        animal = Animal.builder()
                .name("Rex")
                .race("Labrador")
                .birthDate(LocalDate.of(2020, 5, 15))
                .color("Golden")
                .weight(25.5)
                .animalGuardian(animalGuardian)
                .vaccines(Collections.emptyList())
                .build();

        request = new AnimalRequest();
        request.setName("Rex");
        request.setRace("Labrador");
        request.setBirthDate(LocalDate.of(2020, 5, 15));
        request.setColor("Golden");
        request.setWeight(25.5);
        request.setAnimalGuardianId(1L);
    }

    @Test
    void save_ShouldSaveAnimal_WhenValidRequest() {
        when(animalGuardianRepository.findById(anyLong())).thenReturn(Optional.of(animalGuardian));
        when(animalRepository.existsByNameAndAnimalGuardianId(anyString(), anyLong())).thenReturn(false);
        when(animalRepository.save(any(Animal.class))).thenReturn(animal);

        animalService.save(request);

        verify(animalRepository, times(1)).save(any(Animal.class));
    }

    @Test
    void save_ShouldThrowValidationException_WhenAnimalWithSameNameExistsForSameGuardian() {
        when(animalGuardianRepository.findById(anyLong())).thenReturn(Optional.of(animalGuardian));
        when(animalRepository.existsByNameAndAnimalGuardianId(anyString(), anyLong())).thenReturn(true);

        ValidationException exception = assertThrows(ValidationException.class,
                () -> animalService.save(request));

        assertEquals("Animal with name " + request.getName() + " already exists", exception.getMessage());
        verify(animalRepository, never()).save(any(Animal.class));
    }

    @Test
    void save_ShouldThrowNotFoundException_WhenAnimalGuardianNotFound() {
        when(animalGuardianRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> animalService.save(request));

        assertEquals("AnimalGuardian with id " + request.getAnimalGuardianId() + " not found", exception.getMessage());
        verify(animalRepository, never()).save(any(Animal.class));
    }

    @Test
    void getAnimalById_ShouldReturnAnimal_WhenIdExists() {
        when(animalRepository.findById(anyLong())).thenReturn(Optional.of(animal));

        AnimalResponse response = animalService.getAnimalById(1L);

        assertNotNull(response);
        assertEquals(animal.getId(), response.getId());
        assertEquals(animal.getName(), response.getName());
        assertEquals(animal.getRace(), response.getRace());
        assertEquals(animal.getBirthDate(), response.getBirthDate());
        assertEquals(animal.getColor(), response.getColor());
        assertEquals(animal.getWeight(), response.getWeight());
    }

    @Test
    void getAnimalById_ShouldThrowNotFoundException_WhenIdNotExists() {
        when(animalRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> animalService.getAnimalById(1L));

        assertEquals("Animal with id 1 not found", exception.getMessage());
    }

    @Test
    void getAnimalByLikedName_ShouldReturnList_WhenNameMatches() {
        List<Animal> animals = Arrays.asList(
                animal,
                Animal.builder()
                        .name("Max")
                        .race("Beagle")
                        .birthDate(LocalDate.of(2019, 3, 10))
                        .color("Brown")
                        .weight(15.0)
                        .animalGuardian(animalGuardian)
                        .vaccines(Collections.emptyList())
                        .build()
        );

        when(animalRepository.findByLikedName(anyString())).thenReturn(animals);

        List<AnimalResponse> response = animalService.getAnimalByLikedName("e");

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(animals.get(0).getName(), response.get(0).getName());
        assertEquals(animals.get(1).getName(), response.get(1).getName());
    }

    @Test
    void getAnimalByLikedName_ShouldThrowNotFoundException_WhenNoMatch() {
        when(animalRepository.findByLikedName(anyString())).thenReturn(Collections.emptyList());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> animalService.getAnimalByLikedName("nonexistent"));

        assertEquals("No Animal found with name containing: nonexistent", exception.getMessage());
    }

    @Test
    void updateAnimal_ShouldUpdateAnimal_WhenValidRequest() {
        when(animalRepository.findById(anyLong())).thenReturn(Optional.of(animal));
        when(animalRepository.save(any(Animal.class))).thenReturn(animal);

        animalService.updateAnimal(1L, request);

        verify(animalRepository, times(1)).save(any(Animal.class));
    }

    @Test
    void updateAnimal_ShouldThrowNotFoundException_WhenAnimalNotExists() {
        when(animalRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> animalService.updateAnimal(1L, request));

        assertEquals("Animal with id " + request.getId() + " not found", exception.getMessage());
        verify(animalRepository, never()).save(any(Animal.class));
    }

    @Test
    void deleteAnimalById_ShouldDeleteAnimal_WhenIdExists() {
        when(animalRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(animalRepository).deleteById(anyLong());

        animalService.deleteAnimalById(1L);

        verify(animalRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteAnimalById_ShouldThrowNotFoundException_WhenIdNotExists() {
        when(animalRepository.existsById(anyLong())).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> animalService.deleteAnimalById(1L));

        assertEquals("Animal with id 1 not found", exception.getMessage());
        verify(animalRepository, never()).deleteById(anyLong());
    }
}
