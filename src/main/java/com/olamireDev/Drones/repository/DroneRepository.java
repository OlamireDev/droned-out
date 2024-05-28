package com.olamireDev.Drones.repository;

import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.entity.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<DroneEntity, String> {

    List<DroneEntity> findAllByState(DroneState droneState);

}
