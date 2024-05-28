package com.olamireDev.Drones.repository;

import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

    @Query("SELECT d FROM DeliveryEntity d WHERE d.droneEntity.serialNumber = ?1 AND d.droneEntity.state =?2 ORDER BY d.deliveryStartDate DESC limit 1")
    Optional<DeliveryEntity> findLastDeliveryByDrone(String serialNumber, DroneState state);

}
