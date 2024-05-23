package com.olamireDev.Drones.repository;

import com.olamireDev.Drones.data.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<MedicationEntity, Long> {
}
