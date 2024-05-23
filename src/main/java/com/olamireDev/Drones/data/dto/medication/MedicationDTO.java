package com.olamireDev.Drones.data.dto.medication;

import com.olamireDev.Drones.data.entity.MedicationEntity;
import com.olamireDev.Drones.utils.RegexHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MedicationDTO {

    private String name;

    private Double weight;

    private String code;

    private String image;

    public boolean isValid() {
        return name != null && name.matches(RegexHolder.MEDICATION_NAME_REGEX) && weight != null && code != null
                && code.matches(RegexHolder.MEDICATION_CODE_REGEX) && image != null && !image.isEmpty() && image.length() < 1024 *1024 * 5;
    }

    public MedicationEntity toEntity() {
        return MedicationEntity.builder()
                .name(name)
                .weight(weight)
                .code(code)
                .image(image)
                .build();
    }

}
