package cue.edu.co.velocerentals.mapping.DTO;

import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;

import java.math.BigDecimal;
import java.time.Year;

public record VehiclesDTo(Integer id, String type, String make,
                          String model, Integer year, BigDecimal pricePerDay, String  status) {
}
