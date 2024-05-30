package com.olamireDev.Drones.service;

import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.dto.drone.DroneRequestDTO;
import com.olamireDev.Drones.data.dto.drone.DroneResponseDTO;
import com.olamireDev.Drones.data.entity.DroneEntity;
import com.olamireDev.Drones.exception.BadRequestException;
import com.olamireDev.Drones.repository.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DroneService {

    @Autowired
    DroneRepository droneRepository;

    public DroneResponseDTO requestRegisterDrone(DroneRequestDTO droneRequestDTO) {
        droneRepository.findById(droneRequestDTO.getSerialNumber()).ifPresent(droneEntity -> {
            throw new BadRequestException("Drone with serial number already exists");
        });
        DroneEntity droneEntity = droneRepository.save(droneRequestDTO.toEntity());
        return DroneResponseDTO.fromEntity(droneEntity);
    }

    public List<DroneResponseDTO> getAvailableDrones() {
        return droneRepository.findAllByState(DroneState.IDLE).stream().map(DroneResponseDTO::fromEntity).toList();
    }

    public String getDroneBatteryLevel(String serialNumber) {
        return droneRepository.findById(serialNumber)
                .map(drone-> "Drone "+serialNumber +" battery level: " + drone.getBatteryCapacity() + " %")
                .orElseThrow(() -> new BadRequestException("Drone with serial number does not exist"));
    }

    @Scheduled(fixedDelay = 1000 * 60)
    public void auditDroneHealth(){
        var drones = droneRepository.findAll();
        log.info("Auditing drone health");
        log.info("{} drones being tracked", drones.size());
        log.info("\tDrone Serial Number\t|\tBattery Level(%)\t|\tState");
        log.info("------------------------|-----------------------|--------------------");
        drones.forEach(drone -> log.info("\t{}\t\t|\t\t{}\t\t\t|\t{}", drone.getSerialNumber(), drone.getBatteryCapacity(), drone.getState()));
        log.info("Auditing complete");
        log.info("");
    }

}
