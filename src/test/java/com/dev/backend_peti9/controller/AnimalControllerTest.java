package com.dev.backend_peti9.controller;

import com.dev.backend_peti9.dto.AnimalRequest;
import com.dev.backend_peti9.dto.AnimalResponse;
import com.dev.backend_peti9.exception.NotFoundException;
import com.dev.backend_peti9.service.AnimalService;
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
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AnimalControllerTest {

    @Mock
    private AnimalService animalService;

    @InjectMocks
    private AnimalController animalController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private AnimalRequest request;
    private AnimalResponse response;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(animalController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        request = new AnimalRequest();
        request.setName("Rex");
        request.setRace("Labrador");
        request.setBirthDate(LocalDate.of(2020, 5, 15));
        request.setColor("Golden");
        request.setWeight(25.5);
        request.setAnimalGuardianId(1L);

        response = new AnimalResponse(
                1L,
                "Rex",
                "Labrador",
                LocalDate.of(2020, 5, 15),
                "Golden",
                25.5,
                Collections.emptyList()
        );
    }

    @Test
    void save_ShouldReturnCreated_WhenRequestIsValid() throws Exception {
        doNothing().when(animalService).save(any(AnimalRequest.class));

        mockMvc.perform(post("/api/v1/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAnimalById_ShouldReturnAnimal_WhenIdExists() throws Exception {
        when(animalService.getAnimalById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/animals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Rex"))
                .andExpect(jsonPath("$.race").value("Labrador"))
                .andExpect(jsonPath("$.color").value("Golden"))
                .andExpect(jsonPath("$.weight").value(25.5));
    }

    @Test
    void getAnimalById_ShouldReturnNotFound_WhenIdDoesNotExist() throws Exception {
        when(animalService.getAnimalById(999L))
                .thenThrow(new NotFoundException("Animal not found"));

        mockMvc.perform(get("/api/v1/animals/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAnimalByLikedName_ShouldReturnList_WhenNameMatches() throws Exception {
        List<AnimalResponse> responses = Arrays.asList(
                response,
                new AnimalResponse(
                        2L,
                        "Max",
                        "Beagle",
                        LocalDate.of(2019, 3, 10),
                        "Brown",
                        15.0,
                        Collections.emptyList()
                )
        );

        when(animalService.getAnimalByLikedName(anyString())).thenReturn(responses);

        mockMvc.perform(get("/api/v1/animals/name/e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Rex"))
                .andExpect(jsonPath("$[1].name").value("Max"));
    }

    @Test
    void getAnimalByLikedName_ShouldReturnNotFound_WhenNoMatch() throws Exception {
        when(animalService.getAnimalByLikedName(anyString()))
                .thenThrow(new NotFoundException("No animals found"));

        mockMvc.perform(get("/api/v1/animals/name/nonexistent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateAnimal_ShouldReturnOk_WhenRequestIsValid() throws Exception {
        doNothing().when(animalService).updateAnimal(anyLong(), any(AnimalRequest.class));

        mockMvc.perform(put("/api/v1/animals/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAnimalById_ShouldReturnNoContent_WhenIdExists() throws Exception {
        doNothing().when(animalService).deleteAnimalById(1L);

        mockMvc.perform(delete("/api/v1/animals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
