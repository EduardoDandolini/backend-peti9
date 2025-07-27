package com.dev.backend_peti9.service;

import com.dev.backend_peti9.dto.AnimalGuardianRequest;
import com.dev.backend_peti9.dto.AnimalGuardianResponse;
import com.dev.backend_peti9.exception.NotFoundException;
import com.dev.backend_peti9.exception.ValidationException;
import com.dev.backend_peti9.model.AnimalGuardian;
import com.dev.backend_peti9.repository.AnimalGuardianRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalGuardianServiceTest {

    @Mock
    private AnimalGuardianRepository animalGuardianRepository;

    @InjectMocks
    private AnimalGuardianService animalGuardianService;

    private AnimalGuardian animalGuardian;
    private AnimalGuardianRequest request;

    @BeforeEach
    void setUp() {
        animalGuardian = AnimalGuardian.builder()
                .name("Eduardo")
                .surname("Edu")
                .birthDate(LocalDate.of(2004, 12, 6))
                .animals(Collections.emptyList())
                .build();

        request = new AnimalGuardianRequest();
        request.setName("Eduardo");
        request.setSurname("Edu");
        request.setBirthDate(LocalDate.of(2004, 12, 6));
    }

    @Test
    void save_ShouldSaveAnimalGuardian_WhenNameNotExists() {
        when(animalGuardianRepository.existsByName(anyString())).thenReturn(false);
        when(animalGuardianRepository.save(any(AnimalGuardian.class))).thenReturn(animalGuardian);

        animalGuardianService.save(request);

        verify(animalGuardianRepository, times(1)).existsByName(request.getName());
        verify(animalGuardianRepository, times(1)).save(any(AnimalGuardian.class));
    }

    @Test
    void save_ShouldThrowValidationException_WhenNameAlreadyExists() {
        when(animalGuardianRepository.existsByName(anyString())).thenReturn(true);

        ValidationException exception = assertThrows(ValidationException.class,
                () -> animalGuardianService.save(request));

        assertEquals("AnimalGuardian with name " + request.getName() + " already exists",
                exception.getMessage());
        verify(animalGuardianRepository, never()).save(any(AnimalGuardian.class));
    }

    @Test
    void getAnimalGuardianById_ShouldReturnAnimalGuardian_WhenIdExists() {
        when(animalGuardianRepository.findById(1L)).thenReturn(Optional.of(animalGuardian));

        AnimalGuardianResponse response = animalGuardianService.getAnimalGuardianById(1L);

        assertNotNull(response);
        assertEquals(animalGuardian.getId(), response.getId());
        assertEquals(animalGuardian.getName(), response.getName());
        assertEquals(animalGuardian.getSurname(), response.getSurname());
        assertEquals(animalGuardian.getBirthDate(), response.getBirthDate());
    }

    @Test
    void getAnimalGuardianById_ShouldThrowNotFoundException_WhenIdNotExists() {
        when(animalGuardianRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> animalGuardianService.getAnimalGuardianById(1L));

        assertEquals("AnimalGuardian with id 1 not found", exception.getMessage());
    }

    @Test
    void getAnimalGuardianByLikedName_ShouldReturnListOfAnimalGuardians_WhenNameMatches() {
        List<AnimalGuardian> animalGuardians = Arrays.asList(
                animalGuardian,
                AnimalGuardian.builder()
                        .name("Eduarda")
                        .surname("Duda")
                        .birthDate(LocalDate.of(2005, 1, 1))
                        .animals(Collections.emptyList())
                        .build()
        );

        when(animalGuardianRepository.findByLikedName("edu")).thenReturn(animalGuardians);

        List<AnimalGuardianResponse> responses = animalGuardianService.getAnimalGuardianByLikedName("edu");

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals(animalGuardian.getName(), responses.get(0).getName());
        assertEquals("Eduarda", responses.get(1).getName());
    }

    @Test
    void getAnimalGuardianByLikedName_ShouldThrowNotFoundException_WhenNoMatchFound() {
        when(animalGuardianRepository.findByLikedName("nonexistent")).thenReturn(Collections.emptyList());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> animalGuardianService.getAnimalGuardianByLikedName("nonexistent"));

        assertEquals("No AnimalGuardian found with name containing: nonexistent", exception.getMessage());
    }
}
