package com.olamireDev.Drones.data.dto.loadmedication.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olamireDev.Drones.data.dto.medication.LoadedMedicationsDTO;
import lombok.Getter;

import java.util.Set;

@Getter
public class LoadDroneResponseDTO {

    @JsonProperty("load_drone_response_error")
    private LoadDroneResponseErrorType loadingMedicationResponseError;

    @JsonProperty("loaded_medications")
    private Set<LoadedMedicationsDTO> loadedMedications;

    @JsonProperty("failed_medication_ids")
    private Set<Long> failedMedicationIds;

    private LoadDroneResponseDTO(LoadDroneResponseErrorType loadingMedicationResponseError, Set<LoadedMedicationsDTO> loadedMedications, Set<Long> failedMedicationIds) {
        this.loadingMedicationResponseError = loadingMedicationResponseError;
        this.loadedMedications = loadedMedications;
        this.failedMedicationIds = failedMedicationIds;
    }

    public static LoadDroneResponseDTO success(Set<LoadedMedicationsDTO> loadedMedications, Set<Long> failedMedicationIds) {
        return new LoadDroneResponseDTO(null, loadedMedications, failedMedicationIds);
    }

    public static LoadDroneResponseDTO error(LoadDroneResponseErrorType loadingMedicationResponseError) {
        return new LoadDroneResponseDTO(loadingMedicationResponseError, null, null);
    }


}
