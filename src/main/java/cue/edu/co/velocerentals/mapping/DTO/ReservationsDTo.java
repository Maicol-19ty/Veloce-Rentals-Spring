package cue.edu.co.velocerentals.mapping.DTO;

import cue.edu.co.velocerentals.models.User;
import cue.edu.co.velocerentals.models.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record ReservationsDTo(Integer id, User user, Vehicle vehicle,
                              LocalDate startDate, LocalDate endDate, BigDecimal totalCost) {
}
