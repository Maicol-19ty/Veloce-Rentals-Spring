package cue.edu.co.velocerentals.mapping.dto;

import cue.edu.co.velocerentals.domain.models.User;
import cue.edu.co.velocerentals.domain.models.Vehicle;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ReservationsDTO(Integer id, User user, Vehicle vehicle,
                              LocalDate startDate, LocalDate endDate, BigDecimal totalCost) {
}
