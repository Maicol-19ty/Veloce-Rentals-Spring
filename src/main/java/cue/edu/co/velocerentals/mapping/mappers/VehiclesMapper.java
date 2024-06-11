package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.dto.VehiclesDTO;
import cue.edu.co.velocerentals.domain.models.Vehicle;

// Mapper class for converting between Vehicles and VehiclesDTo objects.
public class VehiclesMapper {

    // Method to map from Vehicles model to VehiclesDTo DTO.
    public static VehiclesDTO mapFromModel(Vehicle vehicles) {
        return new VehiclesDTO(vehicles.getId(), vehicles.getType(), vehicles.getMake(), vehicles.getModel(),
                vehicles.getYear(), vehicles.getPricePerDay(), vehicles.getStatus());
    }

    // Method to map from VehiclesDTo DTO to Vehicles model.
    public static Vehicle mapFromDTO(VehiclesDTO vehiclesDTo) {
        return Vehicle.builder()
                .id(vehiclesDTo.id())
                .type(vehiclesDTo.type())
                .make(vehiclesDTo.make())
                .model(vehiclesDTo.model())
                .year(vehiclesDTo.year())
                .pricePerDay(vehiclesDTo.pricePerDay())
                .status(vehiclesDTo.status())
                .build();
    }

}
