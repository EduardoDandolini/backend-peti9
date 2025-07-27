package com.dev.backend_peti9.controller;

import com.dev.backend_peti9.dto.AnimalGuardianRequest;
import com.dev.backend_peti9.dto.AnimalGuardianResponse;
import com.dev.backend_peti9.service.AnimalGuardianService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AnimalGuardianControllerTest {

    @Mock
    private AnimalGuardianService animalGuardianService;

    @InjectMocks
    private AnimalGuardianController animalGuardianController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private AnimalGuardianRequest request;
    private AnimalGuardianResponse response;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(animalGuardianController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        request = new AnimalGuardianRequest();
        request.setName("João Carlos");
        request.setSurname("JC");
        request.setBirthDate(LocalDate.of(1990, 8, 25));

        response = new AnimalGuardianResponse(
                1L,
                "João Carlos",
                "JC",
                LocalDate.of(1990, 8, 25),
                null
        );
    }

    @Test
    void save_ShouldReturnCreated_WhenRequestIsValid() throws Exception {
        doNothing().when(animalGuardianService).save(any(AnimalGuardianRequest.class));

        mockMvc.perform(post("/api/v1/animal-guardian")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void getAnimalGuardianById_ShouldReturnAnimalGuardian_WhenIdExists() throws Exception {
        when(animalGuardianService.getAnimalGuardianById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/animal-guardian/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("João Carlos"))
                .andExpect(jsonPath("$.surname").value("JC"));
    }

    @Test
    void getAnimalGuardianById_ShouldReturnNotFound_WhenIdDoesNotExist() throws Exception {
        when(animalGuardianService.getAnimalGuardianById(999L))
                .thenThrow(new RuntimeException("AnimalGuardian not found"));

        mockMvc.perform(get("/api/v1/animal-guardian/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAnimalGuardianByLikedName_ShouldReturnList_WhenNameMatches() throws Exception {
        List<AnimalGuardianResponse> responses = Arrays.asList(
                response,
                new AnimalGuardianResponse(2L, "João Paulo", "JC", LocalDate.of(1991, 1, 1), null)
        );
        when(animalGuardianService.getAnimalGuardianByLikedName(anyString())).thenReturn(responses);

        mockMvc.perform(get("/api/v1/animal-guardian/name/joão")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("João Carlos"))
                .andExpect(jsonPath("$[1].name").value("João Paulo"))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getAnimalGuardianByLikedName_ShouldReturnBadRequest_WhenNoMatch() throws Exception {
        when(animalGuardianService.getAnimalGuardianByLikedName(anyString()))
                .thenThrow(new RuntimeException("No matches found"));

        mockMvc.perform(get("/api/v1/animal-guardian/name/nonexistent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
