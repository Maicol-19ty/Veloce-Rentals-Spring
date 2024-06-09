package cue.edu.co.velocerentals.mapping.DTO;

import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;
import lombok.*;

import java.math.BigDecimal;

@Builder
public record VehiclesDTo(Integer id, VehicleType type, String make,
                          String model, Integer year, BigDecimal pricePerDay, VehicleStatus status) {
}
