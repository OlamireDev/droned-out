package com.olamireDev.Drones.controller;

import com.olamireDev.Drones.data.dto.loadmedication.request.LoadDroneRequestDTO;
import com.olamireDev.Drones.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping("/load-drone")
    public ResponseEntity<?> loadDroneWithMedication(@RequestBody @Valid LoadDroneRequestDTO requestDTO) {
        return ResponseEntity.ok(deliveryService.loadDrone(requestDTO));
    }

    @GetMapping("/loaded-medications/{droneSerialNumber}")
    public ResponseEntity<?> checkLoadedMedications(@PathVariable String droneSerialNumber) {
        return ResponseEntity.ok(deliveryService.checkLoadedDroneMedications(droneSerialNumber));
    }

}
