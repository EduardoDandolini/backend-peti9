package com.dev.backend_peti9.service;

import com.dev.backend_peti9.dto.VaccineRequest;
import com.dev.backend_peti9.exception.NotFoundException;
import com.dev.backend_peti9.model.Animal;
import com.dev.backend_peti9.model.Vaccine;
import com.dev.backend_peti9.repository.AnimalRepository;
import com.dev.backend_peti9.repository.VaccineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VaccineServiceTest {

    @Mock
    private VaccineRepository vaccineRepository;

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private VaccineService vaccineService;

    private Animal animal;
    private VaccineRequest vaccineRequest;

    @BeforeEach
    void setUp() {
        animal = Animal.builder()
                .name("Toto")
                .race("Vira Lata")
                .birthDate(LocalDate.of(2020, 1, 1))
                .color("Yellow")
                .weight(25.5)
                .build();

        vaccineRequest = new VaccineRequest();
        vaccineRequest.setAnimalId(1L);
        vaccineRequest.setApplicationDate(LocalDate.now());
        vaccineRequest.setType("Raiva");
    }

    @Test
    void save_ShouldSaveVaccine_WhenAnimalExists() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        when(vaccineRepository.save(any(Vaccine.class))).thenAnswer(invocation -> invocation.getArgument(0));

        vaccineService.save(vaccineRequest);

        verify(animalRepository).findById(1L);

        ArgumentCaptor<Vaccine> vaccineCaptor = ArgumentCaptor.forClass(Vaccine.class);
        verify(vaccineRepository).save(vaccineCaptor.capture());

        Vaccine savedVaccine = vaccineCaptor.getValue();
        assertNotNull(savedVaccine);
        assertEquals(vaccineRequest.getType(), savedVaccine.getType());
        assertEquals(vaccineRequest.getApplicationDate(), savedVaccine.getApplicationDate());
        assertEquals(animal, savedVaccine.getAnimal());
    }

    @Test
    void save_ShouldThrowNotFoundException_WhenAnimalNotExists() {
        when(animalRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> vaccineService.save(vaccineRequest));

        assertEquals("Animal with id 1 not found", exception.getMessage());
        verify(vaccineRepository, never()).save(any(Vaccine.class));
    }

    @Test
    void save_ShouldSetVaccineAnimal_WhenSaving() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        when(vaccineRepository.save(any(Vaccine.class))).thenAnswer(invocation -> invocation.getArgument(0));

        vaccineService.save(vaccineRequest);

        ArgumentCaptor<Vaccine> vaccineCaptor = ArgumentCaptor.forClass(Vaccine.class);
        verify(vaccineRepository).save(vaccineCaptor.capture());

        Vaccine savedVaccine = vaccineCaptor.getValue();
        assertNotNull(savedVaccine.getAnimal());
        assertEquals(animal.getId(), savedVaccine.getAnimal().getId());
    }
}
