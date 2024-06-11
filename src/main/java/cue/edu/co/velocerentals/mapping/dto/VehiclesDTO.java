package cue.edu.co.velocerentals.mapping.dto;

import cue.edu.co.velocerentals.domain.enums.VehicleStatus;
import cue.edu.co.velocerentals.domain.enums.VehicleType;
import lombok.*;

import java.math.BigDecimal;

@Builder
public record VehiclesDTO(Integer id, VehicleType type, String make,
                          String model, Integer year, BigDecimal pricePerDay, VehicleStatus status) {
}
