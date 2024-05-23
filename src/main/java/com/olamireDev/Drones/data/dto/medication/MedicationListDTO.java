package com.olamireDev.Drones.data.dto.medication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MedicationListDTO {

    private List<MedicationDTO> medications;

}
