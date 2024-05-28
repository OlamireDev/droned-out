package com.olamireDev.Drones.service;

import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.dto.loadedmedication.response.LoadedDroneResponseDTO;
import com.olamireDev.Drones.data.dto.loadedmedication.response.LoadedDroneResponseErrorType;
import com.olamireDev.Drones.data.dto.loadmedication.request.LoadDroneRequestDTO;
import com.olamireDev.Drones.data.dto.loadmedication.response.LoadDroneResponseDTO;
import com.olamireDev.Drones.data.dto.loadmedication.response.LoadDroneResponseErrorType;
import com.olamireDev.Drones.data.dto.medication.LoadedMedicationsDTO;
import com.olamireDev.Drones.data.dto.medication.MedicationQuantity;
import com.olamireDev.Drones.data.entity.DeliveryEntity;
import com.olamireDev.Drones.repository.DeliveryRepository;
import com.olamireDev.Drones.repository.DroneRepository;
import com.olamireDev.Drones.repository.MedicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;

@Service
@Slf4j
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    public LoadDroneResponseDTO loadDrone(LoadDroneRequestDTO request) {
        var droneOpt = droneRepository.findById(request.getDroneSerialNumber());
        if(droneOpt.isEmpty()){
            return LoadDroneResponseDTO.error(LoadDroneResponseErrorType.DRONE_NOT_FOUND);
        }
        var drone = droneOpt.get();
        if(!drone.getState().equals(DroneState.IDLE)){
            return LoadDroneResponseDTO.error(LoadDroneResponseErrorType.DRONE_NOT_AVAILABLE);
        }
        if(drone.getBatteryCapacity().compareTo(new BigDecimal(25)) < 0){
            return LoadDroneResponseDTO.error(LoadDroneResponseErrorType.DRONE_BATTERY_LOW);
        }
        drone.setState(DroneState.LOADING);
        drone = droneRepository.save(drone);
        var totalWeight = BigDecimal.ZERO;
        var medicationIds = new HashSet<>(request.getMedicationQuantityList().stream().map(MedicationQuantity::medicationId).toList());
        var successfulMedications = new HashSet<MedicationQuantity>();
        var loadedMedications = new HashSet<LoadedMedicationsDTO>();
        var foundMedications = medicationRepository.findAllByIdIn(medicationIds);
        for(var medication : foundMedications){
            var medicationQuantityDTOOpt = request.getMedicationQuantityList().stream().filter(m -> m.medicationId().equals(medication.getId())).findFirst();
            if(medicationQuantityDTOOpt.isEmpty()){
                log.error("Medication quantity not found for medication id: {}", medication.getId());
                continue;
            }
            var quantity = medicationQuantityDTOOpt.get().quantity();
            var loadedMedicationDTO = LoadedMedicationsDTO.fromEntity(medication, quantity);
            totalWeight = totalWeight.add(medication.getWeight().multiply(new BigDecimal(quantity)));
            if(totalWeight.compareTo(drone.getWeightLimit()) > 0){
                drone.setState(DroneState.IDLE);
                droneRepository.save(drone);
                return LoadDroneResponseDTO.error(LoadDroneResponseErrorType.DRONE_WEIGHT_LIMIT_EXCEEDED);
            }
            medicationIds.remove(medication.getId());
            loadedMedications.add(loadedMedicationDTO);
            successfulMedications.add(medicationQuantityDTOOpt.get());
        }
        drone.setState(DroneState.LOADED);
        var deliveryEntity = DeliveryEntity.builder().droneEntity(drone).medicationQuantitySet(successfulMedications).build();
        deliveryRepository.save(deliveryEntity);
        return LoadDroneResponseDTO.success(loadedMedications, new HashSet<>(medicationIds));
    }

    public LoadedDroneResponseDTO checkLoadedDroneMedications(String droneSerialNumber) {
        var droneOpt = droneRepository.findById(droneSerialNumber);
        if(droneOpt.isEmpty()){
            return LoadedDroneResponseDTO.error(LoadedDroneResponseErrorType.DRONE_NOT_FOUND);
        }
        var drone = droneOpt.get();
        if(!drone.getState().equals(DroneState.LOADED)){
            return LoadedDroneResponseDTO.error(LoadedDroneResponseErrorType.DRONE_NOT_IN_LOADED_STATE);
        }
        var deliveryOpt = deliveryRepository.findLastDeliveryByDrone(drone.getSerialNumber(), DroneState.LOADED);
        if(deliveryOpt.isEmpty()){
            return LoadedDroneResponseDTO.error(LoadedDroneResponseErrorType.NO_DELIVERY_FOR_PROVIDED_DRONE);
        }
        var delivery = deliveryOpt.get();
        var loadedMedications = new HashSet<LoadedMedicationsDTO>();
        for(var medicationQuantity : delivery.getMedicationQuantitySet()){
            var medicationOpt = medicationRepository.findById(medicationQuantity.medicationId());
            if(medicationOpt.isEmpty()){
                log.error("Medication not found for id: {}", medicationQuantity.medicationId());
                continue;
            }
            var loadedMedicationDTO = LoadedMedicationsDTO.fromEntity(medicationOpt.get(), medicationQuantity.quantity());
            loadedMedications.add(loadedMedicationDTO);
        }
        return LoadedDroneResponseDTO.success(loadedMedications);
    }


}
