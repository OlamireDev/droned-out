package com.olamireDev.Drones.data.entity;

import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.discrete.DroneModelType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneEntity {

    @Id
    private String serialNumber;

    private DroneModelType model;

    private BigDecimal weightLimit;

    private BigDecimal batteryCapacity;

    private DroneState state;

    @CreationTimestamp
    private LocalDateTime registrationDate;

}
