package com.olamireDev.Drones.controller;

import com.olamireDev.Drones.data.dto.drone.DroneRequestDTO;
import com.olamireDev.Drones.data.dto.drone.DroneResponseDTO;
import com.olamireDev.Drones.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drones")
public class DroneController {

    @Autowired
    DroneService droneService;

    @PostMapping("/register")
    public ResponseEntity<DroneResponseDTO> registerDrone(@RequestBody @Valid DroneRequestDTO requestDTO) {
        return ResponseEntity.ok(droneService.requestRegisterDrone(requestDTO));
    }

    @GetMapping("/available")
    public ResponseEntity<List<DroneResponseDTO>> getAvailableDrones() {
        return ResponseEntity.ok(droneService.getAvailableDrones());
    }

    @GetMapping("/battery/{serialNumber}")
    public ResponseEntity<String> getDroneBatteryLevel(@PathVariable String serialNumber) {
        return ResponseEntity.ok(droneService.getDroneBatteryLevel(serialNumber));
    }

}
