package com.olamireDev.Drones.controller;

import com.olamireDev.Drones.data.discrete.DroneModelType;
import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.dto.drone.DroneRequestDTO;
import com.olamireDev.Drones.exception.BadRequestException;
import com.olamireDev.Drones.repository.DroneRepository;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DroneControllerTest {

    @Autowired
    private DroneController droneController;

    @Autowired
    DroneRepository droneRepository;

    @AfterEach
    void tearDown() {
        droneRepository.deleteAll();
    }

    @Test
    @DisplayName("Return success After registering one drone")
    void registerOneDrone() {
        var goodDroneRequest = new DroneRequestDTO("123QWE2102035", new BigDecimal("500"), new BigDecimal("54"), DroneState.IDLE, DroneModelType.LW);
        var response = droneController.registerDrone(goodDroneRequest);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("123QWE2102035", response.getBody().getSerialNumber());
    }

    @Test
    @DisplayName("Return Error After registering two drones with same serial number")
    void registerTwoDronesSameSerialNumber() {
        var goodDroneRequest = new DroneRequestDTO("123QWE2102035", new BigDecimal("500"), new BigDecimal("54"), DroneState.IDLE, DroneModelType.LW);
        var response = droneController.registerDrone(goodDroneRequest);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("123QWE2102035", response.getBody().getSerialNumber());
        assertThrows(BadRequestException.class,() -> droneController.registerDrone(goodDroneRequest));
    }

    @Test
    @DisplayName("Return Error After registering two drones with same serial number")
    void registerTwoDronesDifferentSerialNumber() {
        var goodDroneRequest1 = new DroneRequestDTO("123QWE2102035", new BigDecimal("500"), new BigDecimal("54"), DroneState.IDLE, DroneModelType.LW);
        var response = droneController.registerDrone(goodDroneRequest1);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("123QWE2102035", response.getBody().getSerialNumber());
        var goodDroneRequest2 = new DroneRequestDTO("123QWE2102037", new BigDecimal("500"), new BigDecimal("54"), DroneState.IDLE, DroneModelType.LW);
        response = droneController.registerDrone(goodDroneRequest2);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("123QWE2102037", response.getBody().getSerialNumber());
    }

    @Test
    @DisplayName("Return a list of all available drones")
    void getAvailableDrones() {
        registerTwoDronesDifferentSerialNumber();
        var response = droneController.getAvailableDrones();
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    @DisplayName("Return battery level of drone")
    void getDroneBatteryLevel() {
        registerTwoDronesDifferentSerialNumber();
        var response = droneController.getDroneBatteryLevel("123QWE2102035");
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("54"));
    }

    @Test
    @DisplayName("Return battery level of drone, Drone does not exist")
    void getDroneBatteryLevelInvalidDrone() {
        registerTwoDronesDifferentSerialNumber();
        assertThrows(BadRequestException.class, () -> droneController.getDroneBatteryLevel("Sigma5"));
    }


}