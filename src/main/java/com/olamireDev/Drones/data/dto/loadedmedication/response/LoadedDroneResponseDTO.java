package com.olamireDev.Drones.data.dto.loadedmedication.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olamireDev.Drones.data.dto.medication.LoadedMedicationsDTO;
import lombok.Getter;

import java.util.Set;

@Getter
public class LoadedDroneResponseDTO {

    @JsonProperty("loaded_drone_response_error")
    private LoadedDroneResponseErrorType loadedDroneResponseError;

    @JsonProperty("loaded_medications")
    private Set<LoadedMedicationsDTO> loadedMedications;

    public LoadedDroneResponseDTO(LoadedDroneResponseErrorType loadedDroneResponseError, Set<LoadedMedicationsDTO> loadedMedications) {
        this.loadedDroneResponseError = loadedDroneResponseError;
        this.loadedMedications = loadedMedications;
    }

    public static LoadedDroneResponseDTO success(Set<LoadedMedicationsDTO> loadedMedications) {
        return new LoadedDroneResponseDTO(null, loadedMedications);
    }

    public static LoadedDroneResponseDTO error(LoadedDroneResponseErrorType loadedDroneResponseError) {
        return new LoadedDroneResponseDTO(loadedDroneResponseError, null);
    }

}
