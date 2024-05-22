package com.olamireDev.Drones.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MedicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //(allowed only letters, numbers, ‘-‘, ‘_’);

    private Double weight;

    private String code; //(allowed only upper case letters, underscore and numbers);

    private String image; //(picture of the medication case).

}
