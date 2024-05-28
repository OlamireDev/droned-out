package com.olamireDev.Drones.data.dto.drone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olamireDev.Drones.data.discrete.DroneState;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseDroneDTO {

    @NotBlank(message = "Drone serialNumber is required")
    @Size(max = 100, message = "Serial number is not more than 100 characters")
    @JsonProperty("serial_number")
    private String serialNumber;

    @NotNull(message = "Drone weight limit is required")
    @DecimalMax(value = "500", message = "Drone weight limit is 500 grams maximum")
    @JsonProperty("weight_limit")
    private BigDecimal weightLimit;

    @NotNull(message = "Drone battery capacity is required")
    @DecimalMax(value = "100", message = "Drone battery capacity cannot exceed 100")
    @JsonProperty("battery_capacity")
    private BigDecimal batteryCapacity;

    private DroneState state;

}
