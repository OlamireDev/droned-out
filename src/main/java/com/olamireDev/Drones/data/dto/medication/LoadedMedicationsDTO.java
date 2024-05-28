package com.olamireDev.Drones.data.dto.medication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olamireDev.Drones.data.entity.MedicationEntity;

import java.util.Objects;

public record LoadedMedicationsDTO(@JsonProperty("medication_id") Long medicationId, @JsonProperty("medication_name") String medicationName, Long quantity) {

    public static LoadedMedicationsDTO fromEntity(MedicationEntity entity, Long quantity) {
        return new LoadedMedicationsDTO(entity.getId(), entity.getName(), quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoadedMedicationsDTO that)) return false;
        return Objects.equals(medicationId, that.medicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(medicationId);
    }

}
