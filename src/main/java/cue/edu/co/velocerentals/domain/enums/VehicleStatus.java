package cue.edu.co.velocerentals.domain.enums;

import lombok.Getter;

// Enum representing vehicle statuses
@Getter
public enum VehicleStatus {

    available("available"),  // Available for rent
    rented("rented"),        // Currently rented
    maintenance("maintenance");  // Under maintenance

    // Getter
    private final String status;  // String representation

    // Constructor
    VehicleStatus(String status) {
        this.status = status;
    }

    // Method to get VehicleStatus from its string
    public static VehicleStatus fromString(String status) {
        for (VehicleStatus v : VehicleStatus.values()) {
            if (v.status.equalsIgnoreCase(status)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with text " + status + " found");
    }
}
