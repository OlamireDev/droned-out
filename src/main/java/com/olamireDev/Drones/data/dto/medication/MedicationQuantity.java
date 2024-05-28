package com.olamireDev.Drones.data.dto.medication;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record MedicationQuantity(@NotNull Long medicationId, @NotNull Long quantity) {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicationQuantity that)) return false;
        return Objects.equals(medicationId, that.medicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(medicationId);
    }

}
