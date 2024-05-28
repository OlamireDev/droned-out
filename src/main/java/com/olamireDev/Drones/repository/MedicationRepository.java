package com.olamireDev.Drones.repository;

import com.olamireDev.Drones.data.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MedicationRepository extends JpaRepository<MedicationEntity, Long> {

    List<MedicationEntity> findAllByIdIn(Set<Long> ids);

}
