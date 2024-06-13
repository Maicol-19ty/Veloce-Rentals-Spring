package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.dto.VehiclesDTO;
import cue.edu.co.velocerentals.domain.models.Vehicle;

// Mapper class for converting between Vehicles and VehiclesDTo objects.
public class VehiclesMapper {

    // Method to map from Vehicles model to VehiclesDTo DTO.
    public static VehiclesDTO mapFromModel(Vehicle vehicles) {
        return new VehiclesDTO(vehicles.getType(), vehicles.getMake(), vehicles.getModel(),
                vehicles.getYear(), vehicles.getPricePerDay(), vehicles.getStatus());
    }

    // Method to map from VehiclesDTo DTO to Vehicles model.
    public static Vehicle mapFromDTO(VehiclesDTO vehiclesDTO) {
        return Vehicle.builder()
                .type(vehiclesDTO.type())
                .make(vehiclesDTO.make())
                .model(vehiclesDTO.model())
                .year(vehiclesDTO.year())
                .pricePerDay(vehiclesDTO.pricePerDay())
                .status(vehiclesDTO.status())
                .build();
    }

}
