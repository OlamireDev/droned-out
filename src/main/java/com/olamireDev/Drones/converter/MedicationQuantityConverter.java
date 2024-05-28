package com.olamireDev.Drones.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olamireDev.Drones.data.dto.medication.MedicationQuantity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Set;

@Converter(autoApply = true)
public class MedicationQuantityConverter implements AttributeConverter<Set<MedicationQuantity>, String> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Set<MedicationQuantity> medicationQuantities) {

        try {
            return objectMapper.writeValueAsString(medicationQuantities);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<MedicationQuantity> convertToEntityAttribute(String dbData) {
        return objectMapper.convertValue(dbData, new TypeReference<Set<MedicationQuantity>>() {
        });
    }
}
