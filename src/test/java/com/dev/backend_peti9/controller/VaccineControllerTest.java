package com.dev.backend_peti9.controller;

import com.dev.backend_peti9.dto.VaccineRequest;
import com.dev.backend_peti9.service.VaccineService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VaccineControllerTest {

    @Mock
    private VaccineService vaccineService;

    @InjectMocks
    private VaccineController vaccineController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private VaccineRequest request;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vaccineController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        request = new VaccineRequest();
        request.setType("Rabies Vaccine");
        request.setApplicationDate(LocalDate.now());
        request.setAnimalId(1L);
    }

    @Test
    void save_ShouldReturnOk_WhenRequestIsValid() throws Exception {
        doNothing().when(vaccineService).save(any(VaccineRequest.class));

        mockMvc.perform(post("/api/v1/vaccine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void save_ShouldReturnBadRequest_WhenServiceThrowsException() throws Exception {
        doThrow(new RuntimeException("Error saving vaccine"))
                .when(vaccineService).save(any(VaccineRequest.class));

        mockMvc.perform(post("/api/v1/vaccine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
