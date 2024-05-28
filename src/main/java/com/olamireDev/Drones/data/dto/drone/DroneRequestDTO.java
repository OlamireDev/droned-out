package com.olamireDev.Drones.data.dto.drone;

import com.olamireDev.Drones.data.discrete.DroneModelType;
import com.olamireDev.Drones.data.discrete.DroneState;
import com.olamireDev.Drones.data.entity.DroneEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class DroneRequestDTO extends BaseDroneDTO {

    @NotNull(message = "Drone model is required")
    private DroneModelType model;

    public DroneRequestDTO(String serialNumber, BigDecimal weightLimit, BigDecimal batteryCapacity, DroneState state,
                           DroneModelType model) {
        super(serialNumber, weightLimit, batteryCapacity, state);
        this.model = model;
    }

    public DroneEntity toEntity(){
        DroneEntity entity = new DroneEntity();
        entity.setSerialNumber(this.getSerialNumber());
        entity.setWeightLimit(this.getWeightLimit());
        entity.setBatteryCapacity(this.getBatteryCapacity());
        entity.setState(this.getState());
        entity.setModel(this.getModel());
        return entity;
    }

}
