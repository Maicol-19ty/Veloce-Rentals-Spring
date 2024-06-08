package cue.edu.co.velocerentals.enums;

import lombok.Getter;

@Getter
// Enum representing vehicle types
public enum VehicleType {

    CAR("Car"),              // Car type
    MOTORCYCLE("Motorcycle");  // Motorcycle type

    private final String type;  // String representation

    // Constructor
    VehicleType(String type) {
        this.type = type;
    }

    // Method to get VehicleType from its string
    public static VehicleType fromString(String type) {
        for (VehicleType v : VehicleType.values()) {
            if (v.type.equalsIgnoreCase(type)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with text " + type + " found");
    }
}
