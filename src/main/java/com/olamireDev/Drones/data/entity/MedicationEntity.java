package com.olamireDev.Drones.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    private BigDecimal weight;

    private String code; //(allowed only upper case letters, underscore and numbers);

    @Column(columnDefinition = "TEXT")
    private String image; //(picture of the medication case).

    @CreationTimestamp
    private LocalDateTime creationDate;

}
