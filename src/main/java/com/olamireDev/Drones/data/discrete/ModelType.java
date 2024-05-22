package com.olamireDev.Drones.data.discrete;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ModelType {

    LW("Lightweight"),
    MW("Middleweight"),
    CW("Cruiserweight"),
    HW("Heavyweight");

    private final String modelType;
}
