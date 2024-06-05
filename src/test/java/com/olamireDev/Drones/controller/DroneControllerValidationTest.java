package com.olamireDev.Drones.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olamireDev.Drones.data.discrete.DroneModelType;
import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.dto.drone.DroneRequestDTO;
import com.olamireDev.Drones.service.DroneService;
import com.olamireDev.Drones.service.MedicationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DroneController.class)
public class DroneControllerValidationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DroneService droneService;

    @MockBean
    MedicationService medicationService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Validation Triggered when registering drone carrying weight above the maximum")
    void registerDroneWithWeightAboveMaximum() throws Exception {
        var goodDroneRequest = new DroneRequestDTO("123QWE2102035", new BigDecimal("501"), new BigDecimal("54"), DroneState.IDLE, DroneModelType.LW);
        mockMvc.perform(post("/api/v1/drones/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goodDroneRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Drone weight limit is 500 grams maximum"));
    }

    @Test
    @DisplayName("Validation Triggered when registering drone with battery capacity above the maximum")
    void registerDroneWithBatteryAboveNormal() throws Exception {
        var goodDroneRequest = new DroneRequestDTO("123QWE2102035", new BigDecimal("400"), new BigDecimal("1001"), DroneState.IDLE, DroneModelType.LW);
        mockMvc.perform(post("/api/v1/drones/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goodDroneRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Drone battery capacity cannot exceed 100"));
    }

    @Test
    @DisplayName("Validation Triggered when registering drone with serial number above the maximum")
    void registerDroneWithSerialNumberAboveMaximum() throws Exception {
        var goodDroneRequest = new DroneRequestDTO("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                new BigDecimal("400"), new BigDecimal("100"), DroneState.IDLE, DroneModelType.LW);
        mockMvc.perform(post("/api/v1/drones/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goodDroneRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Serial number is not more than 100 characters"));
    }

}
