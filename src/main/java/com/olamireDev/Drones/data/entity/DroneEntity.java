package com.olamireDev.Drones.data.entity;

import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.discrete.ModelType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneEntity {

    @Id
    private String serialNumber; //(100 characters max);

    private ModelType model; //(Lightweight, Middleweight, Cruiserweight, Heavyweight)

    private Double weightLimit; //(500gr max);

    private BigDecimal batteryCapacity; // (percentage);

    private DroneState state; //(IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

}
