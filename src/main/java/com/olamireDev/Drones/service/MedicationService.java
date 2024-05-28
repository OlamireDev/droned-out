package com.olamireDev.Drones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olamireDev.Drones.data.dto.medication.MedicationDTO;
import com.olamireDev.Drones.data.dto.medication.MedicationListDTO;
import com.olamireDev.Drones.data.entity.MedicationEntity;
import com.olamireDev.Drones.repository.MedicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MedicationService {

    @Autowired
    private MedicationRepository medicationRepo;


    public void loadMedications() {
        var medicationResource = new ClassPathResource("files/medications.json");
        try {
            var medicationJsonString = medicationResource.getContentAsString(Charset.defaultCharset());
            var mapper = new ObjectMapper();
            var medicationsListDTO = mapper.readValue(medicationJsonString, MedicationListDTO.class);
            List<MedicationEntity> medicationEntities = new ArrayList<>();
            for (MedicationDTO medicationDTO : medicationsListDTO.getMedications()) {
                if(!medicationDTO.isNameValid()){
                    log.error("Invalid medication name: {}", medicationDTO.getName());
                    continue;
                }
                if(!medicationDTO.isCodeValid()) {
                    log.error("Invalid medication code: {}", medicationDTO.getCode());
                    continue;
                }
                if(!medicationDTO.isWeightValid()) {
                    log.error("Invalid medication weight: {}", medicationDTO.getWeight());
                    continue;
                }
                if(!medicationDTO.isImageValid()) {
                    log.error("Invalid medication image: {}", medicationDTO.getImage());
                    continue;
                }
                medicationEntities.add(medicationDTO.toEntity());
            }
            medicationEntities = medicationRepo.saveAll(medicationEntities);
            log.info("Loaded {} medications", medicationEntities.size());
            log.info("Medications: {}", medicationEntities.stream().map(medicationEntity -> medicationEntity.getId() + " : " +medicationEntity.getName())
                    .collect(Collectors.toList()));
        }catch (Exception e){
            log.error("Error loading medications", e);
        }
    }


}
