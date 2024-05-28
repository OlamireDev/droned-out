package com.olamireDev.Drones.data.dto.loadmedication.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olamireDev.Drones.data.dto.medication.MedicationQuantity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoadDroneRequestDTO {

    @JsonProperty("drone_serial_number")
    @NotNull(message = "Drone serial number is required")
    private String droneSerialNumber;

    @JsonProperty("medication_quantity_map")
    @Size(min = 1, message = "At least one medication id is required")
    private Set<MedicationQuantity> medicationQuantityList;

}
