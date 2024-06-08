package cue.edu.co.velocerentals.enums;

// Enum representing vehicle statuses
public enum VehicleStatus {

    AVAILABLE("Available"),  // Available for rent
    RENTED("Rented"),        // Currently rented
    MAINTENANCE("Maintenance");  // Under maintenance

    private final String status;  // String representation

    // Constructor
    VehicleStatus(String type) {
        this.status = type;
    }

    // Getter
    public String getType() {
        return this.status;
    }

    // Method to get VehicleStatus from its string
    public static VehicleStatus fromString(String type) {
        for (VehicleStatus v : VehicleStatus.values()) {
            if (v.status.equalsIgnoreCase(type)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with text " + type + " found");
    }
}
