package com.olamireDev.Drones.data.entity;

import com.olamireDev.Drones.data.dto.medication.MedicationQuantity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private DroneEntity droneEntity;

    private Set<MedicationQuantity> medicationQuantitySet;

    @CreationTimestamp
    private LocalDateTime deliveryStartDate;

    private LocalDateTime deliveryEndDate;

}
