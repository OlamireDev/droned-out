package com.olamireDev.Drones.data.dto.drone;


import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.entity.DroneEntity;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class DroneResponseDTO extends BaseDroneDTO {

    private String model;

    public DroneResponseDTO(String serialNumber, BigDecimal weightLimit, BigDecimal batteryCapacity, DroneState state, String model) {
        super(serialNumber, weightLimit, batteryCapacity, state);
        this.model = model;
    }

    public static DroneResponseDTO fromEntity(DroneEntity entity){
        return new DroneResponseDTO(entity.getSerialNumber(), entity.getWeightLimit(),
                entity.getBatteryCapacity(), entity.getState(), entity.getModel().getModelType());
    }

}
